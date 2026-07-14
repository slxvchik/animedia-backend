package dev.animedia.contentservice.genre.application.usecase.admin;

import dev.animedia.contentservice.genre.application.dto.request.UpdateGenreDto;

public interface UpdateGenreUseCase {
	void update(UpdateGenreDto genreDto);
}
