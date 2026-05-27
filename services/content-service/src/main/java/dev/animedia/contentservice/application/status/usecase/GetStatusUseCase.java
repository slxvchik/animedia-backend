package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import jakarta.annotation.Nullable;

public interface GetStatusUseCase {
    StatusDto get(Long id, @Nullable Boolean active, @Nullable String languageCode);
}
