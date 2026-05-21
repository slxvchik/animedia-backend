package dev.animedia.contentservice.infrastructure.persistence.status.dto;

public record StatusTranslationRowDto(
	Long id,
	String alias,
	Integer sortOrder,
	Long translationId,
	String languageCode,
	String name
) {}