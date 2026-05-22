package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import jakarta.annotation.Nullable;

public interface GetStatusUseCase {
    StatusDto get(Long id, boolean onlyActive, @Nullable String languageCode);
}
