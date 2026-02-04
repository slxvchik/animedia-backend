package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreTranslationPageService {
	Page<GenreTranslationResponseDto> findAll(Pageable pageable);
	Page<GenreTranslationResponseDto> findByIds(List<Long> ids, Pageable pageable);
	Page<GenreTranslationResponseDto> findByGenreId(Long genreId, Pageable pageable);
	Page<GenreTranslationResponseDto> findByLanguageCode(String languageCode, Pageable pageable);
}
