package dev.animedia.contentservice.content.dto.request;

import dev.animedia.contentservice.content.model.Content;

import java.util.UUID;

public record ContentTranslationRequestDto(
	UUID uuid,
	Content content,
	String languageCode,
	String title,
	String description
) {}
