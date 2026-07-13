package dev.animedia.contentservice.status.application.event;

import dev.animedia.contentservice.shared.domain.event.Event;
import dev.animedia.contentservice.status.application.dto.StatusDto;

public class StatusCreateEvent extends Event {
	private final StatusDto status;

	public StatusCreateEvent(StatusDto status) {
		this.status = status;
	}

	public StatusDto getStatus() {
		return status;
	}
}
