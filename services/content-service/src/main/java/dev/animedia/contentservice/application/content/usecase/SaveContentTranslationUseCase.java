package dev.animedia.contentservice.application.content.usecase;

import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;

import java.util.UUID;

public interface SaveContentTranslationUseCase {
	void saveTranslation(UUID contentUuid, ContentTranslationDto contentTranslationDto);
}
