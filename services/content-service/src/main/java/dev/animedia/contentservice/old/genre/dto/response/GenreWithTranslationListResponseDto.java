package dev.animedia.contentservice.old.genre.dto.response;

import java.util.List;

public record GenreWithTranslationListResponseDto(
    Long id,
    String alias,
    Long sort,
    List<GenreTranslationResponseDto> translations
) {}
