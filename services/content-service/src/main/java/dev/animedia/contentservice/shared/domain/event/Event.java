package dev.animedia.contentservice.shared.domain.event;

import java.time.Instant;

public abstract class Event {
	private final Instant occuredOn = Instant.now();

	public Instant getOccuredOn() {
		return occuredOn;
	}
}