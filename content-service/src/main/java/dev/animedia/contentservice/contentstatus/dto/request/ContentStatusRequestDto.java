package dev.animedia.contentservice.contentstatus.dto.request;

import dev.animedia.contentservice.contentstatus.ContentStatusConstants;
import jakarta.validation.constraints.NotBlank;

public record ContentStatusRequestDto(
	@NotBlank(message = ContentStatusConstants.LANGUAGE_ALIAS_REQUIRED_MESSAGE)
	String alias
) {}
