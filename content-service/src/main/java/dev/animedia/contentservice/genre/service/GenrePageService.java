package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenrePageService {
	Page<GenreResponseDto> findAll(Pageable pageable);
	Page<GenreResponseDto> findByIds(List<Long> ids, Pageable pageable);
	Page<GenreResponseDto> findByAliases(List<String> aliases, Pageable pageable);

	Page<GenreWithTranslationResponseDto> findByLanguage(String languageCode, Pageable pageable);
	Page<GenreWithTranslationResponseDto> findByIdsAndLanguageCode(List<Long> ids, String languageCode, Pageable pageable);
	Page<GenreWithTranslationResponseDto> findByAliasesAndLanguage(List<String> aliases, String languageCode, Pageable pageable);
}
