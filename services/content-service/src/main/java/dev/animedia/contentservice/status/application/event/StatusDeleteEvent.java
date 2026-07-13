package dev.animedia.contentservice.status.application.event;

import dev.animedia.contentservice.shared.domain.event.Event;

import java.util.UUID;

public class StatusDeleteEvent extends Event {
	private final UUID statusId;

	public StatusDeleteEvent(UUID statusId) {
		this.statusId = statusId;
	}

	public UUID getStatusId() {
		return statusId;
	}
}
