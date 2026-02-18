package dev.animedia.contentservice.contentstatus.dto.response;

import java.util.List;

public record ContentStatusWithTranslationsResponseDto(
    Long id,
    String alias,
    List<ContentStatusTranslationResponseDto> translations
) {}
