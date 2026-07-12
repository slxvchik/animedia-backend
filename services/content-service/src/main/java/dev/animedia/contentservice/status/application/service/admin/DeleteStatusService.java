package dev.animedia.contentservice.status.application.service.admin;

import dev.animedia.contentservice.status.application.exception.StatusNotFoundException;
import dev.animedia.contentservice.status.application.usecase.admin.DeleteStatusUseCase;
import dev.animedia.contentservice.shared.domain.event.EventDispatcherInterface;
import dev.animedia.contentservice.status.application.event.StatusDeleteEvent;
import dev.animedia.contentservice.status.domain.repository.StatusCommandRepository;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;

import java.util.UUID;

public class DeleteStatusService implements DeleteStatusUseCase {
    private final StatusQueryRepository statusQueryRepository;
    private final StatusCommandRepository statusCommandRepository;
    private final EventDispatcherInterface eventDispatcherInterface;

    public DeleteStatusService(
        StatusQueryRepository statusQueryRepository,
        StatusCommandRepository statusCommandRepository,
        EventDispatcherInterface eventDispatcherInterface
    ) {
        this.statusQueryRepository = statusQueryRepository;
        this.statusCommandRepository = statusCommandRepository;
	    this.eventDispatcherInterface = eventDispatcherInterface;
    }

    @Override
    public void delete(UUID id) {
        statusQueryRepository.findById(id, null)
            .orElseThrow(() -> new StatusNotFoundException(id));
        statusCommandRepository.delete(id);
        eventDispatcherInterface.dispatch(new StatusDeleteEvent(id));
    }
}
