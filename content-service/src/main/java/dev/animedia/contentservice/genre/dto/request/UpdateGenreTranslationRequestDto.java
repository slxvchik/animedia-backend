package dev.animedia.contentservice.genre.dto.request;

public record UpdateGenreTranslationRequestDto(
    Long id,
    String name,
    String description
) {}
