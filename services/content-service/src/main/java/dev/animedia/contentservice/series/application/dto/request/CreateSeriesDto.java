package dev.animedia.contentservice.series.application.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public record CreateSeriesDto(
	String contentId,
	Integer episode,
	Boolean active,
	Integer duration,
	LocalDate releaseDate,
	String videoId
) {}
