package dev.animedia.contentservice.application.status.usecase.translation;

import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;

public interface UpdateStatusTranslationUseCase {
    StatusTranslationDto update(StatusTranslationDto statusTranslationDto);
}
