package dev.animedia.contentservice.genre.application.usecase.admin;

import dev.animedia.contentservice.genre.application.dto.GenreDto;

import java.util.UUID;

public interface CreateGenreUseCase {
	UUID create(GenreDto genreDto);
}
