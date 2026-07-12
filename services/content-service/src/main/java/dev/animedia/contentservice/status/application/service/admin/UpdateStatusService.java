package dev.animedia.contentservice.status.application.service.admin;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.status.application.exception.StatusNotFoundException;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.status.application.usecase.admin.UpdateStatusUseCase;
import dev.animedia.contentservice.shared.domain.event.EventDispatcherInterface;
import dev.animedia.contentservice.status.application.event.StatusUpdateEvent;
import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.repository.StatusCommandRepository;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;

import java.util.stream.Collectors;

public class UpdateStatusService implements UpdateStatusUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;
    private final StatusCommandRepository statusCommandRepository;
    private final EventDispatcherInterface eventDispatcherInterface;

    public UpdateStatusService(
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository,
        StatusCommandRepository statusCommandRepository,
        EventDispatcherInterface eventDispatcherInterface
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
        this.statusCommandRepository = statusCommandRepository;
	    this.eventDispatcherInterface = eventDispatcherInterface;
    }

    @Override
    public void update(StatusDto statusDto) {
        Status status = statusQueryRepository.findById(statusDto.id(), null)
            .orElseThrow(() -> new StatusNotFoundException(statusDto.id()));

        status.update(
            statusDto.sortOrder(),
            statusDto.active(),
            statusDto.translationSet()
                .stream()
                .map(statusApplicationMapper::toStatusTranslation)
                .collect(Collectors.toSet())
        );

        statusCommandRepository.update(status);

        Status updated = statusQueryRepository.findById(status.getId(), null)
            .orElseThrow(() -> new StatusNotFoundException(status.getId()));

        eventDispatcherInterface.dispatch(
            new StatusUpdateEvent(
                statusApplicationMapper.toStatusDto(updated)
            )
        );
    }
}
