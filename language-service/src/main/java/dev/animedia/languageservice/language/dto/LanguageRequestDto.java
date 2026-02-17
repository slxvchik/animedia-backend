package dev.animedia.languageservice.language.dto;

import dev.animedia.languageservice.language.LanguageConstants;
import jakarta.validation.constraints.NotBlank;

public record LanguageRequestDto(
	@NotBlank(message = LanguageConstants.LANGUAGE_CODE_REQUIRED_MESSAGE)
    String code,
	@NotBlank(message = LanguageConstants.LANGUAGE_NAME_REQUIRED_MESSAGE)
    String name
) {}
