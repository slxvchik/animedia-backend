package dev.animedia.contentservice.content.dto.request;

import java.util.UUID;

public record ContentTranslationRequestDto(
	UUID contentUuid,
	String languageCode,
	String title,
	String description
) {}
