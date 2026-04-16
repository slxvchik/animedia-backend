package dev.animedia.contentservice.application.content.usecase;

import dev.animedia.contentservice.application.content.dto.ContentDto;

public interface UpdateContentUseCase {
	ContentDto update(ContentDto contentDto);
}
