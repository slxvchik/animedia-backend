package dev.animedia.contentservice.series.application.usecase.admin;

import dev.animedia.contentservice.series.application.dto.request.UpdateSeriesDto;

public interface UpdateSeriesUseCase {
	void update(UpdateSeriesDto seriesDto);
}
