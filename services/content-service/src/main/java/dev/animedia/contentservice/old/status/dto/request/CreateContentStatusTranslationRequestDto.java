package dev.animedia.contentservice.old.status.dto.request;

import dev.animedia.contentservice.old.status.ContentStatusConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateContentStatusTranslationRequestDto(
	@NotNull(message = ContentStatusConstants.CONTENT_STATUS_ID_REQUIRED_MESSAGE)
	Long contentStatusId,
	@NotBlank(message = ContentStatusConstants.CONTENT_STATUS_LANGUAGE_ID_REQUIRED_MESSAGE)
	String languageCode,
	@NotBlank(message = ContentStatusConstants.CONTENT_STATUS_NAME_REQUIRED_MESSAGE)
	String name
) {}
