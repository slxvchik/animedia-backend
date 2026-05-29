package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import jakarta.annotation.Nullable;

import java.util.UUID;

public interface GetStatusUseCase {
    StatusDto get(UUID id, @Nullable Boolean active, @Nullable String languageCode);
}
