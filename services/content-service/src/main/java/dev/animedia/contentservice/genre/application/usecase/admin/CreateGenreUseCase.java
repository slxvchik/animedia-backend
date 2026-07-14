package dev.animedia.contentservice.genre.application.usecase.admin;

import dev.animedia.contentservice.genre.application.dto.request.CreateGenreDto;

import java.util.UUID;

public interface CreateGenreUseCase {
	UUID create(CreateGenreDto genreDto);
}
