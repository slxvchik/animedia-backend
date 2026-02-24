package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;

public interface ContentStatusQueryService {

	ContentStatusResponseDto findById(Long id);
	ContentStatusWithTranslationResponseDto findByIdAndLanguageCode(Long id, String languageCode);

	boolean existsById(Long id);
	boolean existsByAlias(String alias);
	boolean existsByAliasExcludingId(String alias, Long id);
}
