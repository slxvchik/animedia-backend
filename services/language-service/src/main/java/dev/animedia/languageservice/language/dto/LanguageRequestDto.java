package dev.animedia.languageservice.language.dto;

import dev.animedia.languageservice.language.LanguageErrorConstants;
import jakarta.validation.constraints.NotBlank;

public record LanguageRequestDto(
	@NotBlank(message = LanguageErrorConstants.LANGUAGE_CODE_REQUIRED_MESSAGE)
    String code,
	@NotBlank(message = LanguageErrorConstants.LANGUAGE_NAME_REQUIRED_MESSAGE)
    String name
) {}
