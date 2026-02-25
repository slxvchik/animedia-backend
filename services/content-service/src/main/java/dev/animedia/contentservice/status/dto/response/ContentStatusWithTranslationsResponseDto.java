package dev.animedia.contentservice.status.dto.response;

import java.util.List;

public record ContentStatusWithTranslationsResponseDto(
    Long id,
    String alias,
    List<ContentStatusTranslationResponseDto> translations
) {}
