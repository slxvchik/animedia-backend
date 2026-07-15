package dev.animedia.contentservice.movie.application.usecase.admin;

import dev.animedia.contentservice.movie.application.dto.request.UpdateMovieDto;

public interface UpdateMovieUseCase {
	void update(UpdateMovieDto movieDto);
}
