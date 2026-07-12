package dev.animedia.contentservice.shared.domain.event;

public interface EventDispatcherInterface {
	void dispatch(EventInterface event);
}
