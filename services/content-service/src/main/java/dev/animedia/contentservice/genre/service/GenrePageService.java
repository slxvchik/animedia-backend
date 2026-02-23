package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenrePageService {
	Page<GenreWithTranslationsResponseDto> search(List<String> aliases, List<String> names, List<String> languageCodes, Pageable pageable);
	Page<GenreWithTranslationResponseDto> search(List<String> aliases, List<String> names, String languageCode, Pageable pageable);
}
