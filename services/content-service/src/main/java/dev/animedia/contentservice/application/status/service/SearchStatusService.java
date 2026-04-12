package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.shared.mapper.PaginatonApplicationMapper;
import dev.animedia.contentservice.application.status.dto.SearchStatusDto;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.SearchStatusUseCase;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusSearchCriteria;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchStatusService implements SearchStatusUseCase {
    private final PaginatonApplicationMapper paginatonApplicationMapper;
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;

    @Autowired
    public SearchStatusService(
        PaginatonApplicationMapper paginatonApplicationMapper,
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository
    ) {
        this.paginatonApplicationMapper = paginatonApplicationMapper;
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
    }

    @Override
    public Page<StatusDto> search(SearchStatusDto searchStatusDto) {
        StatusSearchCriteria statusSearchCriteria = statusApplicationMapper.toStatusSearchCriteria(searchStatusDto);
        Page<Status> statusPage = statusQueryRepository.search(statusSearchCriteria, searchStatusDto.pageable());
        return paginatonApplicationMapper.changeContent(statusPage, statusApplicationMapper::toStatusDto);
    }
}
