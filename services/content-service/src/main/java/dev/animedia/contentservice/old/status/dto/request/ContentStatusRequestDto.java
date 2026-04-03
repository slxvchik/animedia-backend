package dev.animedia.contentservice.old.status.dto.request;

import dev.animedia.contentservice.old.status.ContentStatusConstants;
import jakarta.validation.constraints.Pattern;

public record ContentStatusRequestDto(
	@Pattern(regexp = ContentStatusConstants.CONTENT_STATUS_ALIAS_PATTERN, message = ContentStatusConstants.CONTENT_STATUS_INVALID_ALIAS_PATTERN_MESSAGE)
	String alias
) {}
