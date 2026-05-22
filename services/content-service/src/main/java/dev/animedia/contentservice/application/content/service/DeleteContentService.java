package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.usecase.DeleteContentUseCase;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteContentService implements DeleteContentUseCase {
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

    @Autowired
    public DeleteContentService(
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository
    ) {
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
    }

    @Override
    public void delete(UUID uuid) {
        contentQueryRepository.find(uuid, false, null)
            .orElseThrow(ContentNotFoundException::new);
        contentCommandRepository.delete(uuid);
    }
}
