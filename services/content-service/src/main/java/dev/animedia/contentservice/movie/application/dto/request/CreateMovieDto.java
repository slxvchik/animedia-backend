package dev.animedia.contentservice.movie.application.dto.request;

import java.util.Set;

public record CreateMovieDto(
	String contentId,
	String videoId,
	Integer duration,
	Set<String> languageCodes
) {}
