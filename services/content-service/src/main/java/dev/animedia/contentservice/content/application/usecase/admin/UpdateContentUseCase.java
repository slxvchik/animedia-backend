package dev.animedia.contentservice.content.application.usecase.admin;

import dev.animedia.contentservice.content.application.dto.content.request.UpdateContentDto;

public interface UpdateContentUseCase {
	void update(UpdateContentDto createContentRequestDto);
}
