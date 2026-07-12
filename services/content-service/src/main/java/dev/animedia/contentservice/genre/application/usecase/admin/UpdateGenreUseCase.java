package dev.animedia.contentservice.genre.application.usecase.admin;

import dev.animedia.contentservice.genre.application.dto.GenreDto;

public interface UpdateGenreUseCase {
	void update(GenreDto genreDto);
}
