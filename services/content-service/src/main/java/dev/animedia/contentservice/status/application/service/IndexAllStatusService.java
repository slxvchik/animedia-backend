package dev.animedia.contentservice.status.application.service;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.status.application.usecase.IndexAllStatusUseCase;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;

public class IndexAllStatusService implements IndexAllStatusUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;

    public IndexAllStatusService(
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
    }

    @Override
    public Page<StatusDto> index(Pageable pageable) {
        Page<Status> statusPage = statusQueryRepository.findAll(pageable);
        return statusPage.changeContent(statusApplicationMapper::toStatusDto);
    }
}
