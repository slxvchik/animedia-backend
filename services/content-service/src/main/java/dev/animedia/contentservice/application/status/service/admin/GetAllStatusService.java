package dev.animedia.contentservice.application.status.service.admin;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.admin.GetAllStatusUseCase;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;

public class GetAllStatusService implements GetAllStatusUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;

    public GetAllStatusService(
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
    }

    @Override
    public Page<StatusDto> get(Pageable pageable) {
        Page<Status> statusPage = statusQueryRepository.findAll(pageable);
        return statusPage.changeContent(statusApplicationMapper::toStatusDto);
    }
}
