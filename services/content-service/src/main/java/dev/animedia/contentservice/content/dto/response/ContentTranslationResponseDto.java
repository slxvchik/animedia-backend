package dev.animedia.contentservice.content.dto.response;

public record ContentTranslationResponseDto(
	String uuid,
	String contentUuid,
	String languageCode,
	String title,
	String description
) {}
