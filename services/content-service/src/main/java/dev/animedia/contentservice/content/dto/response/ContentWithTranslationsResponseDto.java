package dev.animedia.contentservice.content.dto.response;

import java.util.List;

public record ContentWithTranslationsResponseDto(
	ContentResponseDto content,
	List<ContentTranslationResponseDto> translations
) {}
