package dev.animedia.contentservice.content.application.event;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
import dev.animedia.contentservice.shared.domain.event.EventInterface;

public record ContentUpdateEvent(ContentResponseDto content) implements EventInterface {
}
