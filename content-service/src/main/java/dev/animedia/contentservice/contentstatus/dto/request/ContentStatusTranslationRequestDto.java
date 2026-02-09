package dev.animedia.contentservice.contentstatus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContentStatusTranslationRequestDto(
	@NotNull
	Long contentStatusId,
	@NotBlank
	String languageCode,
	@NotBlank
	String name
) {}
