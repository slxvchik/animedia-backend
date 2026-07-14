package dev.animedia.contentservice.status.application.usecase.admin;

import dev.animedia.contentservice.status.application.dto.request.UpdateStatusDto;

public interface UpdateStatusUseCase {
    void update(UpdateStatusDto statusDto);
}
