package dev.animedia.contentservice.series.application.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record SeriesDto(
	UUID id,
	String contentId,
	Boolean active,
	Integer duration,
	Integer episode,
	LocalDate releaseDate,
	String videoId
) {}
