package dev.animedia.contentservice.old.content.service;

import dev.animedia.contentservice.old.content.dto.request.ContentTranslationRequestDto;
import dev.animedia.contentservice.old.content.dto.response.ContentTranslationResponseDto;

import java.util.UUID;

public interface ContentTranslationCommandService {
	ContentTranslationResponseDto create(ContentTranslationRequestDto contentRequestDto);
	ContentTranslationResponseDto update(UUID uuid,ContentTranslationRequestDto contentRequestDto);
	void delete(UUID uuid);
}
