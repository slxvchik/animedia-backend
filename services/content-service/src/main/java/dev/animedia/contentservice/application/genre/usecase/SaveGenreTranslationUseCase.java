package dev.animedia.contentservice.application.genre.usecase;

import dev.animedia.contentservice.application.genre.dto.GenreTranslationDto;

public interface SaveGenreTranslationUseCase {
	void saveTranslation(Long genreId, GenreTranslationDto genreTranslationDto);
}