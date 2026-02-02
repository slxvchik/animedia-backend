package dev.animedia.contentservice.genre.dto.response;

public record GenreTranslationResponseDto(
    Long id,
    Long genreId,
    String languageCode,
    String name,
    String description
) {}
