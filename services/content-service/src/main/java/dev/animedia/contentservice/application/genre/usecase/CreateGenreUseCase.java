package dev.animedia.contentservice.application.genre.usecase;

import dev.animedia.contentservice.application.genre.dto.GenreDto;

public interface CreateGenreUseCase {
	GenreDto create(GenreDto genreDto);
}
