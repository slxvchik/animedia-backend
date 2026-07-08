package dev.animedia.contentservice.application.status.usecase.admin;

import dev.animedia.contentservice.application.status.dto.StatusDto;

public interface UpdateStatusUseCase {
    void update(StatusDto statusDto);
}
