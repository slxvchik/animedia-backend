package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.GetStatusListUseCase;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetStatusListService implements GetStatusListUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;

    @Autowired
    public GetStatusListService(
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
    }

    @Override
    public List<StatusDto> getList(List<Long> idList, boolean onlyActive, @Nullable String languageCode) {
        List<Status> statusList = statusQueryRepository.findByIdList(idList, onlyActive, languageCode);
        return statusList
            .stream()
            .map(statusApplicationMapper::toStatusDto)
            .toList();
    }
}
