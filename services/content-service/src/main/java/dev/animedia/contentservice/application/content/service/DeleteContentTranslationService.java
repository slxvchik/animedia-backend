package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.DeleteContentTranslationUseCase;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteContentTranslationService implements DeleteContentTranslationUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

    @Autowired
    public DeleteContentTranslationService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
    }

    @Override
    public void deleteTranslation(UUID contentUuid, UUID contentTranslationUUID) {
        Content content = contentQueryRepository.find(contentUuid, null)
            .orElseThrow(ContentNotFoundException::new);
        content.removeTranslation(contentUuid);
        contentCommandRepository.update(content);
    }
}
