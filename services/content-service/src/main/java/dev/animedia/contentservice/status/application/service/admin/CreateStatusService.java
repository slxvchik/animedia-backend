package dev.animedia.contentservice.status.application.service.admin;

import dev.animedia.contentservice.shared.domain.event.EventDispatcher;
import dev.animedia.contentservice.status.application.dto.request.CreateStatusDto;
import dev.animedia.contentservice.status.application.event.StatusCreateEvent;
import dev.animedia.contentservice.status.application.exception.StatusAliasExistsException;
import dev.animedia.contentservice.status.application.exception.StatusNotFoundException;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.status.application.usecase.admin.CreateStatusUseCase;
import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.repository.StatusCommandRepository;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;

import java.util.UUID;

public class CreateStatusService implements CreateStatusUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusCommandRepository statusCommandRepository;
    private final StatusQueryRepository statusQueryRepository;
    private final EventDispatcher eventDispatcher;

    public CreateStatusService(
        StatusApplicationMapper statusApplicationMapper,
        StatusCommandRepository statusCommandRepository,
        StatusQueryRepository statusQueryRepository,
        EventDispatcher eventDispatcher
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusCommandRepository = statusCommandRepository;
        this.statusQueryRepository = statusQueryRepository;
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    public UUID create(CreateStatusDto statusDto) {
        boolean aliasExists = statusQueryRepository.existsByAlias(statusDto.alias());
        if (aliasExists) {
            throw new StatusAliasExistsException(statusDto.alias());
        }

        Status status = statusApplicationMapper.toStatus(statusDto);
        UUID createdId = statusCommandRepository.create(status);

        Status created = statusQueryRepository.findById(createdId, null)
            .orElseThrow(() -> new StatusNotFoundException(createdId));

        eventDispatcher.dispatch(
            new StatusCreateEvent(
                statusApplicationMapper.toStatusDto(created)
            )
        );

        return createdId;
    }
}
