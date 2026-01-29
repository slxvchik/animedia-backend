package dev.animedia.contentservice.genre.dto.response;

import java.util.List;

public record GenreWithTranslationsResponseDto(
    Long id,
    String alias,
    Long sort,
    List<GenreTranslationResponseDto> translations
) {}
