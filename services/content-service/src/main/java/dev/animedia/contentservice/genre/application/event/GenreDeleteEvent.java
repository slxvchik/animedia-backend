package dev.animedia.contentservice.genre.application.event;

import dev.animedia.contentservice.shared.domain.event.EventInterface;

import java.util.UUID;

public record GenreDeleteEvent(UUID genreId) implements EventInterface {
}
