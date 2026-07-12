package dev.animedia.contentservice.content.application.usecase.user;

import dev.animedia.contentservice.content.application.dto.ContentDto;
import dev.animedia.contentservice.content.domain.model.ContentType;

public interface GetContentDetailUseCase {
	ContentDto get(String alias, ContentType type, int season, String languageCode);
}
