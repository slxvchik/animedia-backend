package dev.animedia.contentservice.genre.application.event;

import dev.animedia.contentservice.shared.domain.event.Event;

import java.util.UUID;

public class GenreDeleteEvent extends Event {
	private final UUID genreId;

	public GenreDeleteEvent(UUID genreId) {
		this.genreId = genreId;
	}

	public UUID getGenreId() {
		return genreId;
	}
}
