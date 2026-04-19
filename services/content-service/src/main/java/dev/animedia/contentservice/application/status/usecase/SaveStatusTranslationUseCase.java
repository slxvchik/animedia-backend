package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;

public interface SaveStatusTranslationUseCase {
    StatusDto saveTranslation(Long statusId, StatusTranslationDto statusTranslationDto);
}
