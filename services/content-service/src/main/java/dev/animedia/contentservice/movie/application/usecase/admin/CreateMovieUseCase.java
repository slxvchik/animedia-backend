package dev.animedia.contentservice.movie.application.usecase.admin;

import dev.animedia.contentservice.movie.application.dto.request.CreateMovieDto;

public interface CreateMovieUseCase {
	void create(CreateMovieDto movieDto);
}
