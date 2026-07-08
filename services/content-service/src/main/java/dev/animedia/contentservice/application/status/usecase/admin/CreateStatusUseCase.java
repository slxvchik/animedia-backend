package dev.animedia.contentservice.application.status.usecase.admin;

import dev.animedia.contentservice.application.status.dto.StatusDto;

import java.util.UUID;

public interface CreateStatusUseCase {
    UUID create(StatusDto statusDto);
}
