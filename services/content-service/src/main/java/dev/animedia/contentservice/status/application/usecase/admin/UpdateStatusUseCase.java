package dev.animedia.contentservice.status.application.usecase.admin;

import dev.animedia.contentservice.status.application.dto.StatusDto;

public interface UpdateStatusUseCase {
    void update(StatusDto statusDto);
}
