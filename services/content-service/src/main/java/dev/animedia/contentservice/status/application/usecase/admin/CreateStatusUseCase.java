package dev.animedia.contentservice.status.application.usecase.admin;

import dev.animedia.contentservice.status.application.dto.request.CreateStatusDto;

import java.util.UUID;

public interface CreateStatusUseCase {
    UUID create(CreateStatusDto statusDto);
}
