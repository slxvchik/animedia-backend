package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusDto;

public interface UpdateStatusUseCase {
    StatusDto update(StatusDto statusDto);
}
