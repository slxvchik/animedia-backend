package dev.animedia.contentservice.content.application.event;

import dev.animedia.contentservice.shared.domain.event.Event;

import java.util.UUID;

public class ContentDeleteEvent extends Event {
	private final UUID contentId;

	public ContentDeleteEvent(UUID contentId) {
		this.contentId = contentId;
	}

	public UUID getContentId() {
		return contentId;
	}
}
