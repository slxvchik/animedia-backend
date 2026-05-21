package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusSearchDto;
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
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;

    @Autowired
    public SearchStatusService(
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
    }

    @Override
    public Page<StatusDto> search(StatusSearchDto searchStatusDto, Pageable pageable) {
        StatusSearchCriteria statusSearchCriteria = statusApplicationMapper.toStatusSearchCriteria(searchStatusDto);
        Page<Status> statusPage = statusQueryRepository.search(statusSearchCriteria, pageable);
        return statusPage.changeContent(statusApplicationMapper::toStatusDto);
    }
}
