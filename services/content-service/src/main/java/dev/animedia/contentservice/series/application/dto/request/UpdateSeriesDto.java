package dev.animedia.contentservice.series.application.dto.request;

import java.time.LocalDate;
import java.util.UUID;

public record UpdateSeriesDto(
	UUID id,
	String contentId,
	Integer episode,
	Boolean active,
	Integer duration,
	LocalDate releaseDate,
	String videoId
) {}
