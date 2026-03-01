package dev.animedia.contentservice.status.service;

import dev.animedia.contentservice.status.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;

import java.util.List;

public interface ContentStatusQueryService {

	ContentStatusResponseDto findById(Long id);
	ContentStatusWithTranslationResponseDto findByIdAndLanguageCode(Long id, String languageCode);
	List<ContentStatusWithTranslationResponseDto> findByIdsAndLanguageCode(List<Long> ids, String languageCode);

	boolean existsById(Long id);
	boolean existsByAlias(String alias);

	boolean existsByAliasExcludingId(String alias, Long id);
}
