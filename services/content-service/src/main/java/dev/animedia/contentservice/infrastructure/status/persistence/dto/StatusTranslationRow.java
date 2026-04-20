package dev.animedia.contentservice.infrastructure.status.persistence.dto;

public record StatusTranslationRow(
	Long id,
	String alias,
	Integer sortOrder,
	Long translationId,
	String languageCode,
	String name
) {}