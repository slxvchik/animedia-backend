package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;

import java.util.List;

public interface ContentStatusTranslationQueryService {
	ContentStatusTranslationResponseDto findById(Long id);
	List<ContentStatusTranslationResponseDto> findByIds(List<Long> ids);
	boolean existsByContentStatusIdAndLanguageCode(Long contentStatusId, String languageCode);
}
