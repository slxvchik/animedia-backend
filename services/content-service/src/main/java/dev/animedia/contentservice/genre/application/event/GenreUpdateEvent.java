package dev.animedia.contentservice.genre.application.event;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.shared.domain.event.Event;

public class GenreUpdateEvent extends Event {
	private final GenreDto genre;

	public GenreUpdateEvent(GenreDto genre) {
		this.genre = genre;
	}

	public GenreDto getGenreDto() {
		return genre;
	}
}
