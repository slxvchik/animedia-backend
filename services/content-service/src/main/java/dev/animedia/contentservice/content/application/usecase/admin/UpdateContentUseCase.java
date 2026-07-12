package dev.animedia.contentservice.content.application.usecase.admin;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;

public interface UpdateContentUseCase {
	void update(ContentRequestDto contentRequestDto);
}
