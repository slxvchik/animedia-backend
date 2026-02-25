package dev.animedia.contentservice.status.dto.request;

import dev.animedia.contentservice.status.ContentStatusConstants;
import jakarta.validation.constraints.NotBlank;

public record UpdateContentStatusTranslationRequestDto(
	@NotBlank(message = ContentStatusConstants.CONTENT_STATUS_NAME_REQUIRED_MESSAGE)
	String name
) {}
