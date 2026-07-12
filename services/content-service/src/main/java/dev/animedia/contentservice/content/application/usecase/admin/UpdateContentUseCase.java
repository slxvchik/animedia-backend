package dev.animedia.contentservice.content.application.usecase.admin;

import dev.animedia.contentservice.content.application.dto.ContentDto;

public interface UpdateContentUseCase {
	void update(ContentDto contentDto);
}
