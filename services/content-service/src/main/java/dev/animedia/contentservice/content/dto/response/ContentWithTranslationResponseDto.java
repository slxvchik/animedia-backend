package dev.animedia.contentservice.content.dto.response;

public record ContentWithTranslationResponseDto(
	ContentResponseDto content,
	ContentTranslationResponseDto translation
) {}
