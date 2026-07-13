package dev.animedia.contentservice.shared.domain.event;

public interface EventDispatcher {
	<T extends Event> void dispatch(T event);
}