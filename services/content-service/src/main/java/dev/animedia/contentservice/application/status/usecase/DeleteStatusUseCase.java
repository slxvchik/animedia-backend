package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusDto;

public interface DeleteStatusUseCase {
    void delete(StatusDto statusDto);
}
