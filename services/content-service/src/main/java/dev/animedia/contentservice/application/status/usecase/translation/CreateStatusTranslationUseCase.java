package dev.animedia.contentservice.application.status.usecase.translation;

import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;

public interface CreateStatusTranslationUseCase {
    StatusTranslationDto create(StatusTranslationDto statusTranslationDto);
}
