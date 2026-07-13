package dev.animedia.contentservice.content.application.event;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
import dev.animedia.contentservice.shared.domain.event.Event;

public class ContentUpdateEvent extends Event {
	private final ContentResponseDto content;

	public ContentUpdateEvent(ContentResponseDto content) {
		this.content = content;
	}

	public ContentResponseDto getContent() {
		return content;
	}
}
