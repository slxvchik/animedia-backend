package dev.animedia.contentservice.contentstatus.dto.request;

import dev.animedia.contentservice.contentstatus.ContentStatusConstants;
import jakarta.validation.constraints.NotBlank;

public record UpdateContentStatusTranslationRequestDto(
	@NotBlank(message = ContentStatusConstants.CONTENT_STATUS_NAME_REQUIRED_MESSAGE)
	String name
) {}
