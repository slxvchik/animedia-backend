package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.status.exception.StatusNotFoundException;
import dev.animedia.contentservice.application.status.usecase.DeleteStatusTranslationUseCase;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusCommandRepository;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteStatusTranslationService implements DeleteStatusTranslationUseCase {
    private final StatusQueryRepository statusQueryRepository;
    private final StatusCommandRepository statusCommandRepository;

    @Autowired
    public DeleteStatusTranslationService(
        StatusQueryRepository statusQueryRepository,
        StatusCommandRepository statusCommandRepository
    ) {
        this.statusQueryRepository = statusQueryRepository;
        this.statusCommandRepository = statusCommandRepository;
    }

    @Override
    public void deleteStatusTranslation(Long statusId, Long statusTranslationId) {
        Status status = statusQueryRepository.findById(statusId, null)
            .orElseThrow(StatusNotFoundException::new);

        status.removeTranslation(statusTranslationId);

        statusCommandRepository.update(status);
    }
}