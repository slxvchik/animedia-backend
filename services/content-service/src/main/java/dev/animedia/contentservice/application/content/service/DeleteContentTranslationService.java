package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.usecase.DeleteContentTranslationUseCase;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;

import java.util.UUID;

public class DeleteContentTranslationService implements DeleteContentTranslationUseCase {
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

    public DeleteContentTranslationService(
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository
    ) {
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
    }

    @Override
    public void deleteTranslation(UUID contentUuid, UUID contentTranslationUUID) {
        Content content = contentQueryRepository.find(contentUuid, false, null)
            .orElseThrow(ContentNotFoundException::new);
        content.removeTranslation(contentUuid);
        contentCommandRepository.update(content);
    }
}
