package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusUserSearchRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;

import java.util.List;

public interface ContentStatusQueryService {

	List<ContentStatusWithTranslationResponseDto> search(ContentStatusUserSearchRequestDto searchRequestDto);
	ContentStatusResponseDto findById(Long id);
	ContentStatusWithTranslationResponseDto findByIdAndLanguageCode(Long id, String languageCode);

	boolean existsById(Long id);
	boolean existsByAlias(String alias);
	boolean existsByAliasExcludingId(String alias, Long id);
}
