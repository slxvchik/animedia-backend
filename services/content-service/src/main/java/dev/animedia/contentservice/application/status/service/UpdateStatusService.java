package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.exception.StatusAliasExistsException;
import dev.animedia.contentservice.application.status.exception.StatusNotFoundException;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.UpdateStatusUseCase;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusCommandRepository;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UpdateStatusService implements UpdateStatusUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;
    private final StatusCommandRepository statusCommandRepository;

    @Autowired
    public UpdateStatusService(
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository,
        StatusCommandRepository statusCommandRepository
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
        this.statusCommandRepository = statusCommandRepository;
    }

    @Transactional
    @Override
    public StatusDto update(StatusDto statusDto) {
        Status status = statusQueryRepository.findById(statusDto.id(), null)
            .orElseThrow(StatusNotFoundException::new);

        boolean aliasExists = statusQueryRepository.existsByAliasExcludeId(statusDto.alias(), statusDto.id());
        if (aliasExists) throw new StatusAliasExistsException();

        status.update(
            statusDto.alias(),
            statusDto.sortOrder(),
            statusDto.translationSet()
                .stream()
                .map(statusApplicationMapper::toStatusTranslation)
                .collect(Collectors.toSet())
        );

        Status updated = statusCommandRepository.update(status);

        return statusApplicationMapper.toStatusDto(updated);
    }
}
