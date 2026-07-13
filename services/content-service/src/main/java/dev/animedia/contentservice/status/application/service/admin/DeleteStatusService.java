package dev.animedia.contentservice.status.application.service.admin;

import dev.animedia.contentservice.shared.domain.event.EventDispatcher;
import dev.animedia.contentservice.status.application.event.StatusDeleteEvent;
import dev.animedia.contentservice.status.application.exception.StatusNotFoundException;
import dev.animedia.contentservice.status.application.usecase.admin.DeleteStatusUseCase;
import dev.animedia.contentservice.status.domain.repository.StatusCommandRepository;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;

import java.util.UUID;

public class DeleteStatusService implements DeleteStatusUseCase {
    private final StatusQueryRepository statusQueryRepository;
    private final StatusCommandRepository statusCommandRepository;
    private final EventDispatcher eventDispatcher;

    public DeleteStatusService(
        StatusQueryRepository statusQueryRepository,
        StatusCommandRepository statusCommandRepository,
        EventDispatcher eventDispatcher
    ) {
        this.statusQueryRepository = statusQueryRepository;
        this.statusCommandRepository = statusCommandRepository;
	    this.eventDispatcher = eventDispatcher;
    }

    @Override
    public void delete(UUID id) {
        statusQueryRepository.findById(id, null)
            .orElseThrow(() -> new StatusNotFoundException(id));
        statusCommandRepository.delete(id);
        eventDispatcher.dispatch(new StatusDeleteEvent(id));
    }
}
