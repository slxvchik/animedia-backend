package dev.animedia.contentservice.application.content.usecase.admin;

import dev.animedia.contentservice.application.content.dto.ContentDto;

public interface UpdateContentUseCase {
	void update(ContentDto contentDto);
}
