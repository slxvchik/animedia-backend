package dev.animedia.contentservice.application.status.usecase.admin;

import dev.animedia.contentservice.application.status.dto.StatusDto;

import java.util.UUID;

public interface GetStatusUseCase {
    StatusDto get(UUID id, String languageCode);
}
