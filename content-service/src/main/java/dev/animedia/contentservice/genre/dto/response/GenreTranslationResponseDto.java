package dev.animedia.contentservice.genre.dto.response;

public record GenreTranslationResponseDto(
    Long id,
    String languageCode,
    String name,
    String descrtiption
) {}
