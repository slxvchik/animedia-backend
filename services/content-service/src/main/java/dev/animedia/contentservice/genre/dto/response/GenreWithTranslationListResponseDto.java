package dev.animedia.contentservice.genre.dto.response;

import java.util.List;

public record GenreWithTranslationListResponseDto(
    Long id,
    String alias,
    Long sort,
    List<GenreTranslationResponseDto> translations
) {}
