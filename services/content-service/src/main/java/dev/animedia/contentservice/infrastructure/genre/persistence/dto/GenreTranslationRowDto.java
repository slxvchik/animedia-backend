package dev.animedia.contentservice.infrastructure.genre.persistence.dto;

public record GenreTranslationRowDto(
    Long id,
    String alias,
    Integer sortOrder,
    Long translationId,
    String languageCode,
    String name,
    String description
) {
}
