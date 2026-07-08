package dev.animedia.contentservice.application.genre.usecase.admin;

import dev.animedia.contentservice.application.genre.dto.GenreDto;

public interface UpdateGenreUseCase {
	void update(GenreDto genreDto);
}
