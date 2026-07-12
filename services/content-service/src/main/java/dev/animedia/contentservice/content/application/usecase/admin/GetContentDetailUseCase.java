package dev.animedia.contentservice.content.application.usecase.admin;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;

import java.util.UUID;

public interface GetContentDetailUseCase {
	ContentResponseDto get(UUID id);
}
