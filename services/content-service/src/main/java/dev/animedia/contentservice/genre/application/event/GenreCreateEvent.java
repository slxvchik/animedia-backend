package dev.animedia.contentservice.genre.application.event;

import dev.animedia.contentservice.genre.application.dto.response.GenreDto;
import dev.animedia.contentservice.shared.domain.event.Event;

public class GenreCreateEvent extends Event {
	private final GenreDto genreDto;

	public GenreCreateEvent(GenreDto genreDto) {
		this.genreDto = genreDto;
	}

	public GenreDto getGenreDto() {
		return genreDto;
	}
}
