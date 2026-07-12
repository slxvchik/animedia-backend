package dev.animedia.contentservice.content.application.service.admin;

import dev.animedia.contentservice.content.application.exception.ContentNotFoundException;
import dev.animedia.contentservice.content.application.usecase.admin.DeleteContentUseCase;
import dev.animedia.contentservice.content.application.event.ContentDeleteEvent;
import dev.animedia.contentservice.content.domain.repository.ContentCommandRepository;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.shared.domain.event.EventDispatcherInterface;

import java.util.UUID;

public class DeleteContentService implements DeleteContentUseCase {
    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;
    private final EventDispatcherInterface eventDispatcherInterface;

    public DeleteContentService(
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository,
        EventDispatcherInterface eventDispatcherInterface
    ) {
        this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
	    this.eventDispatcherInterface = eventDispatcherInterface;
    }

    @Override
    public void delete(UUID id) {
        contentQueryRepository.find(id, null)
            .orElseThrow(() -> new ContentNotFoundException(id));
        contentCommandRepository.delete(id);
        eventDispatcherInterface.dispatch(new ContentDeleteEvent(id));
    }
}
