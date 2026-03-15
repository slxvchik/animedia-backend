package dev.animedia.contentservice.status.dto.response;

import java.util.List;

public record ContentStatusWithTranslationListResponseDto(
    Long id,
    String alias,
    List<ContentStatusTranslationResponseDto> translations
) {}
