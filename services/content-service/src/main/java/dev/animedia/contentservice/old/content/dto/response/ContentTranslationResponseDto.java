package dev.animedia.contentservice.old.content.dto.response;

public record ContentTranslationResponseDto(
	String uuid,
	String contentUuid,
	String languageCode,
	String title,
	String description
) {}
