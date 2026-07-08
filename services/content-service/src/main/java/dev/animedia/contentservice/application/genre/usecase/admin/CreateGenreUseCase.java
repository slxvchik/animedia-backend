package dev.animedia.contentservice.application.genre.usecase.admin;

import dev.animedia.contentservice.application.genre.dto.GenreDto;

import java.util.UUID;

public interface CreateGenreUseCase {
	UUID create(GenreDto genreDto);
}
