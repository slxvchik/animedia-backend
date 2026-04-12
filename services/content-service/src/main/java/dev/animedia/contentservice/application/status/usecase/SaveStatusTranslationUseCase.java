package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;

public interface SaveStatusTranslationUseCase {
    void saveStatusTranslation(Long statusId, StatusTranslationDto statusTranslationDto);
}
