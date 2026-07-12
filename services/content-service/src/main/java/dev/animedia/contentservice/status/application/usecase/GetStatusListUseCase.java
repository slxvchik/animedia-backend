package dev.animedia.contentservice.status.application.usecase;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

public interface GetStatusListUseCase {
	List<StatusDto> get(List<UUID> idList, @Nullable String languageCode);
}
