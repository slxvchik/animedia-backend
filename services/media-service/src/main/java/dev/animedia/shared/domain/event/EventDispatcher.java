package dev.animedia.shared.domain.event;

public interface EventDispatcher {
	<T extends Event> void dispatch(T event);
}