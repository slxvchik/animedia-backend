package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface ContentStatusQueryService {

	List<ContentStatusWithTranslationResponseDto> search(@NonNull String languageCode, @Nullable String alias, @Nullable String name);
	ContentStatusResponseDto findByIdAndLanguageCode(Long id, String languageCode);

	boolean existsByAlias(String alias);
	boolean existsByAliasExcludingId(String alias, Long id);
}
