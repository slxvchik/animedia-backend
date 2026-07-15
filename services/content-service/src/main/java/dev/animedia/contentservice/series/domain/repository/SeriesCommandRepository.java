package dev.animedia.contentservice.series.domain.repository;

import dev.animedia.contentservice.series.domain.model.Series;

import java.util.UUID;

public interface SeriesCommandRepository {
	UUID create(Series series);
	void update(Series series);
	void delete(UUID seriesId);
}
