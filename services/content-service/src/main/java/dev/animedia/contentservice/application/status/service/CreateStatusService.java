package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.exception.StatusAliasExistsException;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.CreateStatusUseCase;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusCommandRepository;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import jakarta.transaction.Transactional;

public class CreateStatusService implements CreateStatusUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusCommandRepository statusCommandRepository;
    private final StatusQueryRepository statusQueryRepository;

    public CreateStatusService(
        StatusApplicationMapper statusApplicationMapper,
        StatusCommandRepository statusCommandRepository,
        StatusQueryRepository statusQueryRepository
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusCommandRepository = statusCommandRepository;
        this.statusQueryRepository = statusQueryRepository;
    }

    @Transactional
    @Override
    public StatusDto create(StatusDto statusDto) {
        boolean aliasExists = statusQueryRepository.existsByAlias(statusDto.alias());
        if (aliasExists) {
            throw new StatusAliasExistsException();
        }

        Status status = statusApplicationMapper.toStatus(statusDto);
        Status created = statusCommandRepository.create(status);

        return statusApplicationMapper.toStatusDto(created);
    }
}
