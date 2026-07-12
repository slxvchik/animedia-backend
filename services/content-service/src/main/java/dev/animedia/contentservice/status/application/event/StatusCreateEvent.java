package dev.animedia.contentservice.status.application.event;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.shared.domain.event.EventInterface;

public record StatusCreateEvent(StatusDto status) implements EventInterface {
}
