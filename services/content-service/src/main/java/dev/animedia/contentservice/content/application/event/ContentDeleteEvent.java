package dev.animedia.contentservice.content.application.event;

import dev.animedia.contentservice.shared.domain.event.EventInterface;

import java.util.UUID;

public record ContentDeleteEvent(UUID contentId) implements EventInterface {
}
