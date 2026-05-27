package dev.animedia.contentservice.application.content.usecase;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import jakarta.annotation.Nullable;

import java.util.UUID;

public interface GetContentByIdUseCase {
	ContentDto get(UUID uuid, @Nullable String languageCode, @Nullable Boolean active);
}
