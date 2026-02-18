package dev.animedia.contentservice.contentstatus.dto.response;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ContentStatusTranslationResponseDto(
    Long id,
	@NotNull
    Long contentStatusId,
	@NotBlank
    String languageCode,
    String name
) {}