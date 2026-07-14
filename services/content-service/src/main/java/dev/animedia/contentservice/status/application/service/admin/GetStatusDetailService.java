package dev.animedia.contentservice.status.application.service.admin;

import dev.animedia.contentservice.status.application.dto.response.StatusDto;
import dev.animedia.contentservice.status.application.exception.StatusNotFoundException;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.status.application.usecase.admin.GetStatusDetailUseCase;
import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;
import jakarta.annotation.Nullable;

import java.util.UUID;

public class GetStatusDetailService implements GetStatusDetailUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;

    public GetStatusDetailService(
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
    }

    @Override
    public StatusDto get(UUID id, @Nullable String languageCode) {
        Status status = statusQueryRepository.findById(id, languageCode)
            .orElseThrow(StatusNotFoundException::new);
        return statusApplicationMapper.toStatusDto(status);
    }
}
