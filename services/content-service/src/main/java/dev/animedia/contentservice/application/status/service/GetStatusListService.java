package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.GetStatusListUseCase;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

public class GetStatusListService implements GetStatusListUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;

    public GetStatusListService(
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
    }

    @Override
    public List<StatusDto> getList(List<UUID> idList, @Nullable Boolean active, @Nullable String languageCode) {
        List<UUID> distinctIdList = idList.stream().distinct().toList();
        List<Status> statusList = statusQueryRepository.findByIdList(distinctIdList, active, languageCode);
        return statusList
            .stream()
            .map(statusApplicationMapper::toStatusDto)
            .toList();
    }
}
