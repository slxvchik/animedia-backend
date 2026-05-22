package dev.animedia.contentservice.infrastructure.persistence.genre.dto;

public record GenreTranslationRowDto(
    Long id,
    String alias,
    Integer sortOrder,
	Boolean active,
    Long translationId,
    String languageCode,
    String name,
    String description
) {
}
