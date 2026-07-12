package dev.animedia.contentservice.status.application.event;

import dev.animedia.contentservice.shared.domain.event.EventInterface;

import java.util.UUID;

public record StatusDeleteEvent(UUID statusId) implements EventInterface {
}
