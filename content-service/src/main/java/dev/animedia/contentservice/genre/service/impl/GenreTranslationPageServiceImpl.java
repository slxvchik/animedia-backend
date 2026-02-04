package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.service.GenreTranslationPageService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GenreTranslationPageServiceImpl implements GenreTranslationPageService {

	@Override
	public Page<GenreTranslationResponseDto> findAll(Pageable pageable) {
		return null;
	}

	@Override
	public Page<GenreTranslationResponseDto> findByGenreId(Long genreId, Pageable pageable) {
		return null;
	}

	@Override
	public Page<GenreTranslationResponseDto> findByLanguageCode(String languageCode, Pageable pageable) {
		return null;
	}
}
