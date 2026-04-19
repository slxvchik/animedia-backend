package dev.animedia.contentservice.application.content.usecase;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentTranslationDto;

import java.util.UUID;

public interface SaveContentTranslationUseCase {
	ContentDto saveTranslation(UUID contentUuid, ContentTranslationDto contentTranslationDto);
}
