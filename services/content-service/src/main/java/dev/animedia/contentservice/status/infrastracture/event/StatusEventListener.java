package dev.animedia.contentservice.status.infrastracture.event;

import dev.animedia.contentservice.status.application.event.StatusCreateEvent;
import dev.animedia.contentservice.status.application.event.StatusDeleteEvent;
import dev.animedia.contentservice.status.application.event.StatusUpdateEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class StatusEventListener {
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onStatusCreate(StatusCreateEvent event) {
		System.out.println("onStatusCreate: " + event.getOccuredOn());
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onStatusUpdate(StatusUpdateEvent event) {
		System.out.println("onStatusUpdate: " + event.getOccuredOn());
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onStatusDelete(StatusDeleteEvent event) {
		System.out.println("onStatusDelete: " + event.getOccuredOn());
	}
}
