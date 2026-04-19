package dev.animedia.contentservice.application.genre.usecase;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreTranslationDto;

public interface SaveGenreTranslationUseCase {
	GenreDto saveTranslation(Long genreId, GenreTranslationDto genreTranslationDto);
}