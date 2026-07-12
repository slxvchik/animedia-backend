package dev.animedia.contentservice.content.application.usecase.admin;

import dev.animedia.contentservice.content.application.dto.ContentDto;

import java.util.UUID;

public interface CreateContentUseCase {
	UUID create(ContentDto contentDto);
}
