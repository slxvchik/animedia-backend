package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.SaveContentTranslationUseCase;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SaveContentTranslationService implements SaveContentTranslationUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

    @Autowired
    public SaveContentTranslationService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
    }

    @Override
    public ContentDto saveTranslation(UUID contentUuid, ContentTranslationDto contentTranslationDto) {
        Content content = contentQueryRepository.find(contentUuid, false, null)
            .orElseThrow(ContentNotFoundException::new);

        ContentTranslation contentTranslation = contentApplicationMapper.toContentTranslation(contentTranslationDto);
        content.saveTranslation(contentTranslation);

        Content updated = contentCommandRepository.update(content);

        return contentApplicationMapper.toContentDto(updated);
    }
}
