package dev.animedia.contentservice.movie.application.usecase;

import dev.animedia.contentservice.movie.application.dto.response.MovieDto;

public interface GetMovieUseCase {
	MovieDto get(String contentId);
}
