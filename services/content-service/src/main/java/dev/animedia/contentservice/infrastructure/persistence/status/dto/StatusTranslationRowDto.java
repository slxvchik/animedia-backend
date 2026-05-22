package dev.animedia.contentservice.infrastructure.persistence.status.dto;

public record StatusTranslationRowDto(
	Long id,
	String alias,
	Integer sortOrder,
	Boolean active,
	Long translationId,
	String languageCode,
	String name
) {}