package dev.animedia.contentservice.status.application.usecase.admin;

import dev.animedia.contentservice.status.application.dto.StatusDto;

import java.util.UUID;

public interface CreateStatusUseCase {
    UUID create(StatusDto statusDto);
}
