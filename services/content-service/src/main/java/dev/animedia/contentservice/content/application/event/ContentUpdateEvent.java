package dev.animedia.contentservice.content.application.event;

import dev.animedia.contentservice.content.application.dto.content.response.ContentDto;
import dev.animedia.contentservice.shared.domain.event.Event;

public class ContentUpdateEvent extends Event {
	private final ContentDto content;

	public ContentUpdateEvent(ContentDto content) {
		this.content = content;
	}

	public ContentDto getContent() {
		return content;
	}
}
