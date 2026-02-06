package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenrePageService {
	Page<GenreWithTranslationsResponseDto> search(String alias, List<String> languageCodes, String name, Pageable pageable);
	Page<GenreWithTranslationResponseDto> search(String alias, String languageCode, String name, Pageable pageable);
}
