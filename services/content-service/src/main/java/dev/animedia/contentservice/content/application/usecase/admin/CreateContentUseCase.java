package dev.animedia.contentservice.content.application.usecase.admin;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;

import java.util.UUID;

public interface CreateContentUseCase {
	UUID create(ContentRequestDto contentRequestDto);
}
