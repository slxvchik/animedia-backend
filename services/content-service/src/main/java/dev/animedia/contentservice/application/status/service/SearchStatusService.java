package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.shared.mapper.PaginationApplicationMapper;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.SearchStatusUseCase;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusSearchCriteria;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchStatusService implements SearchStatusUseCase {
    private final PaginationApplicationMapper paginatonApplicationMapper;
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;

    @Autowired
    public SearchStatusService(
        PaginationApplicationMapper paginatonApplicationMapper,
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository
    ) {
        this.paginatonApplicationMapper = paginatonApplicationMapper;
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
    }

    @Override
    public Page<StatusDto> search(StatusSearchDto searchStatusDto, Pageable pageable) {
        StatusSearchCriteria statusSearchCriteria = statusApplicationMapper.toStatusSearchCriteria(searchStatusDto);
        Page<Status> statusPage = statusQueryRepository.search(statusSearchCriteria, pageable);
        return paginatonApplicationMapper.changeContent(statusPage, statusApplicationMapper::toStatusDto);
    }
}
