package dev.animedia.contentservice.content.application.usecase.admin;

import dev.animedia.contentservice.content.application.dto.content.response.ContentDto;

import java.util.UUID;

public interface GetContentDetailUseCase {
	ContentDto get(UUID id);
}
