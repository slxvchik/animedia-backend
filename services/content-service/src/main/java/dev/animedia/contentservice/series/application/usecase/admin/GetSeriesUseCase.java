package dev.animedia.contentservice.series.application.usecase.admin;

import dev.animedia.contentservice.series.application.dto.response.SeriesDto;

import java.util.UUID;

public interface GetSeriesUseCase {
	SeriesDto get(UUID seriesId);
}
