package dev.animedia.contentservice.content.application.event;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
import dev.animedia.contentservice.shared.domain.event.Event;

public class ContentCreateEvent extends Event {
	private final ContentResponseDto content;

	public ContentCreateEvent(ContentResponseDto content) {
		this.content = content;
	}

	public ContentResponseDto getContent() {
		return content;
	}
}
