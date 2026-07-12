package dev.animedia.contentservice.content.application.event;

import dev.animedia.contentservice.content.application.dto.ContentDto;
import dev.animedia.contentservice.shared.domain.event.EventInterface;

public record ContentCreateEvent(ContentDto content) implements EventInterface {
}
