package dev.animedia.contentservice.old.status.service;

import dev.animedia.contentservice.old.status.dto.request.CreateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.old.status.dto.request.UpdateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.old.status.dto.response.ContentStatusTranslationResponseDto;

public interface ContentStatusTranslationCommandService {
	ContentStatusTranslationResponseDto create(CreateContentStatusTranslationRequestDto createContentStatusTranslationRequestDto);
	ContentStatusTranslationResponseDto update(Long id, UpdateContentStatusTranslationRequestDto updateContentStatusTranslationRequestDto);
	void delete(Long id);
}
