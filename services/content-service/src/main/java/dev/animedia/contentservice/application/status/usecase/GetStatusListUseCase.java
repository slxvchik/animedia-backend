package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

public interface GetStatusListUseCase {
    List<StatusDto> getList(List<UUID> idList, @Nullable Boolean active, @Nullable String languageCode);
}
