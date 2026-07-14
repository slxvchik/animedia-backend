package dev.animedia.contentservice.genre.infrastracture.event;

import dev.animedia.contentservice.shared.domain.event.Event;
import dev.animedia.contentservice.shared.domain.event.EventDispatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class GenreEventDispatcher implements EventDispatcher {
	private final ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public GenreEventDispatcher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public <T extends Event> void dispatch(T event) {
		applicationEventPublisher.publishEvent(event);
	}
}
