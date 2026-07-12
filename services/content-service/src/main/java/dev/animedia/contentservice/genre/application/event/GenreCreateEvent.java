package dev.animedia.contentservice.genre.application.event;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.shared.domain.event.EventInterface;

public record GenreCreateEvent(GenreDto genre) implements EventInterface {
}
