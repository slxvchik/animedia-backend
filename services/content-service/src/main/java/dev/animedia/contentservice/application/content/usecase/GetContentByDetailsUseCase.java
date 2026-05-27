package dev.animedia.contentservice.application.content.usecase;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.domain.content.model.ContentType;
import jakarta.annotation.Nullable;

public interface GetContentByDetailsUseCase {
	ContentDto get(String alias, ContentType type, @Nullable Integer season, @Nullable String languageCode, @Nullable Boolean active);
}
