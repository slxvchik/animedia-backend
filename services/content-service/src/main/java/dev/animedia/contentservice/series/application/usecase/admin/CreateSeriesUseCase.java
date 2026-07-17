package dev.animedia.contentservice.series.application.usecase.admin;

import dev.animedia.contentservice.genre.application.dto.request.CreateGenreDto;

import java.util.UUID;

public interface CreateSeriesUseCase {
	UUID create(CreateGenreDto seriesDto);
}
