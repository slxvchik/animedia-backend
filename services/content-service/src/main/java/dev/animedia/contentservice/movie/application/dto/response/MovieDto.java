package dev.animedia.contentservice.movie.application.dto.response;

import java.util.Set;

public record MovieDto(
	String contentId,
	String videoId,
	Integer duration,
	Set<String> languageCodes
) {}
