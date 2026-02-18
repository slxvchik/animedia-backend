package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;

import java.util.List;

public interface ContentStatusTranslationQueryService {
	ContentStatusTranslationResponseDto findById(Long id);
	ContentStatusTranslationResponseDto findByContentStatusIdAndLanguageCode(Long contentStatusId, String languageCode);
	List<ContentStatusTranslationResponseDto> findByIds(List<Long> ids);

	boolean existsById(Long id);
	boolean existsByContentStatusIdAndLanguageCode(Long contentStatusId, String languageCode);
}
