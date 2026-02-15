package dev.animedia.contentservice.contentstatus.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateContentStatusTranslationRequestDto(
	@NotBlank
	String name
) {}
