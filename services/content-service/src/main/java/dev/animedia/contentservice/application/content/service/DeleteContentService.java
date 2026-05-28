package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.usecase.DeleteContentUseCase;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;

import java.util.UUID;

public class DeleteContentService implements DeleteContentUseCase {
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

    public DeleteContentService(
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository
    ) {
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
    }

    @Override
    public void delete(UUID uuid) {
        contentQueryRepository.find(uuid, null, null)
            .orElseThrow(() -> new ContentNotFoundException(uuid));
        contentCommandRepository.delete(uuid);
    }
}
