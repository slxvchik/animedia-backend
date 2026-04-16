package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;

public interface SaveStatusTranslationUseCase {
    void saveTranslation(Long statusId, StatusTranslationDto statusTranslationDto);
}
