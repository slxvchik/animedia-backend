package dev.animedia.contentservice.genre.dto.request;

public record CreateGenreTranslationRequestDto(
    Long genreId,
    String languageCode,
    String name,
    String desrciption
) {}
