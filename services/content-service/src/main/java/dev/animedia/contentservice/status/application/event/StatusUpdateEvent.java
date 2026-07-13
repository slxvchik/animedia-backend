package dev.animedia.contentservice.status.application.event;

import dev.animedia.contentservice.shared.domain.event.Event;
import dev.animedia.contentservice.status.application.dto.StatusDto;

public class StatusUpdateEvent extends Event {
	private final StatusDto status;

	public StatusUpdateEvent(StatusDto status) {
		this.status = status;
	}

	public StatusDto getStatus() {
		return status;
	}
}
