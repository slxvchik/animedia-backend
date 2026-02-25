package dev.animedia.contentservice.contentstatus.dto.request;

import dev.animedia.contentservice.contentstatus.ContentStatusConstants;
import jakarta.validation.constraints.Pattern;

public record ContentStatusRequestDto(
	@Pattern(regexp = ContentStatusConstants.CONTENT_STATUS_ALIAS_PATTERN, message = ContentStatusConstants.CONTENT_STATUS_INVALID_ALIAS_PATTERN_MESSAGE)
	String alias
) {}
