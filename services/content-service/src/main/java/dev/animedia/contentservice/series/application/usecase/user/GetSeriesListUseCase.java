package dev.animedia.contentservice.series.application.usecase.user;

import dev.animedia.contentservice.series.application.dto.response.SeriesDto;

import java.util.List;

public interface GetSeriesListUseCase {
	List<SeriesDto> getList(String contentId);
}
