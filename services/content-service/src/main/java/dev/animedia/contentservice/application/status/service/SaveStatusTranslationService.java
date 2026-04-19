package dev.animedia.contentservice.application.status.service;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;
import dev.animedia.contentservice.application.status.exception.StatusNotFoundException;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.SaveStatusTranslationUseCase;
import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusTranslation;
import dev.animedia.contentservice.domain.status.repository.StatusCommandRepository;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveStatusTranslationService implements SaveStatusTranslationUseCase {
    private final StatusApplicationMapper statusApplicationMapper;
    private final StatusQueryRepository statusQueryRepository;
    private final StatusCommandRepository statusCommandRepository;

    @Autowired
    public SaveStatusTranslationService(
        StatusApplicationMapper statusApplicationMapper,
        StatusQueryRepository statusQueryRepository,
        StatusCommandRepository statusCommandRepository
    ) {
        this.statusApplicationMapper = statusApplicationMapper;
        this.statusQueryRepository = statusQueryRepository;
        this.statusCommandRepository = statusCommandRepository;
    }

    @Override
    public StatusDto saveTranslation(Long statusId, StatusTranslationDto statusTranslationDto) {
        Status status = statusQueryRepository.findById(statusId, null)
            .orElseThrow(StatusNotFoundException::new);

        StatusTranslation statusTranslation = statusApplicationMapper.toStatusTranslation(statusTranslationDto);

        status.saveTranslation(statusTranslation);

        Status updated = statusCommandRepository.update(status);

        return statusApplicationMapper.toStatusDto(updated);
    }
}
