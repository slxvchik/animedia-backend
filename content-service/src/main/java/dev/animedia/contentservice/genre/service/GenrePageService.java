package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenrePageService {
	Page<GenreWithTranslationsResponseDto> findAll(Pageable pageable);
	Page<GenreWithTranslationsResponseDto> findByAlias(String alias, Pageable pageable);
	Page<GenreWithTranslationsResponseDto> findByAliases(List<String> aliases, Pageable pageable);

	Page<GenreWithTranslationResponseDto> findByAlias(String alias, String languageCode, Pageable pageable);
	Page<GenreWithTranslationResponseDto> findByLanguage(String languageCode, Pageable pageable);
	Page<GenreWithTranslationResponseDto> findByAliasesAndLanguage(List<String> aliases, String languageCode, Pageable pageable);
}
