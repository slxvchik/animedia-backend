package dev.animedia.contentservice.status.application.usecase.admin;

import dev.animedia.contentservice.status.application.dto.response.StatusDto;

import java.util.UUID;

public interface GetStatusDetailUseCase {
    StatusDto get(UUID id, String languageCode);
}
