package dev.animedia.contentservice.content.application.usecase.admin;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;

import java.util.UUID;

public interface GetContentDetailUseCase {
	ContentRequestDto get(UUID id);
}
