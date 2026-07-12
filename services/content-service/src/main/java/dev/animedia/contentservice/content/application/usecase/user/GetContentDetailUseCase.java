package dev.animedia.contentservice.content.application.usecase.user;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
import dev.animedia.contentservice.content.domain.model.ContentType;

public interface GetContentDetailUseCase {
	ContentResponseDto get(String alias, ContentType type, int season, String languageCode);
}
