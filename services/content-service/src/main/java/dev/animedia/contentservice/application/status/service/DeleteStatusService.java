package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.status.exception.StatusNotFoundException;
import dev.animedia.contentservice.application.status.usecase.DeleteStatusUseCase;
import dev.animedia.contentservice.domain.status.repository.StatusCommandRepository;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import jakarta.transaction.Transactional;

public class DeleteStatusService implements DeleteStatusUseCase {
    private final StatusQueryRepository statusQueryRepository;
    private final StatusCommandRepository statusCommandRepository;

    public DeleteStatusService(
        StatusQueryRepository statusQueryRepository,
        StatusCommandRepository statusCommandRepository
    ) {
        this.statusQueryRepository = statusQueryRepository;
        this.statusCommandRepository = statusCommandRepository;
    }

    @Transactional
    @Override
    public void delete(Long id) {
        statusQueryRepository.findById(id, null, null)
            .orElseThrow(StatusNotFoundException::new);
        statusCommandRepository.delete(id);
    }
}
