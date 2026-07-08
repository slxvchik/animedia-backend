package dev.animedia.contentservice.application.content.usecase.admin;

import dev.animedia.contentservice.application.content.dto.ContentDto;

import java.util.UUID;

public interface CreateContentUseCase {
	UUID create(ContentDto contentDto);
}
