package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenreTranslationPageService {
	Page<GenreTranslationResponseDto> findAll(Pageable pageable);
	Page<GenreTranslationResponseDto> findByNameLike(String name, Pageable pageable);
	Page<GenreTranslationResponseDto> findByGenreId(Long genreId, Pageable pageable);
	Page<GenreTranslationResponseDto> findByLanguageCode(String languageCode, Pageable pageable);
}
