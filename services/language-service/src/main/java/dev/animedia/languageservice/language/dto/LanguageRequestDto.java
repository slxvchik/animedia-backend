package dev.animedia.languageservice.language.dto;

import dev.animedia.languageservice.language.LanguageErrorConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LanguageRequestDto(
	@NotBlank(message = LanguageErrorConstants.LANGUAGE_CODE_REQUIRED_MESSAGE)
	@Pattern(regexp = "[a-z]{2}", message = LanguageErrorConstants.LANGUAGE_CODE_INVALID_PATTERN_MESSAGE)
    String code,
	@NotBlank(message = LanguageErrorConstants.LANGUAGE_NAME_REQUIRED_MESSAGE)
    String name,
	String nativeName,
	Boolean isActive,
	Boolean isDefault,
	Integer sortOrder,
	String flagEmoji
) {}
