package dev.animedia.contentservice.content.infrastracture.event;

import dev.animedia.contentservice.content.application.event.ContentCreateEvent;
import dev.animedia.contentservice.content.application.event.ContentDeleteEvent;
import dev.animedia.contentservice.content.application.event.ContentUpdateEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class ContentEventListener {
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onContentCreate(ContentCreateEvent event) {
		System.out.println("onContentCreate: " + event.getOccuredOn());
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onContentUpdate(ContentUpdateEvent event) {
		System.out.println("onContentUpdate: " + event.getOccuredOn());
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onContentDelete(ContentDeleteEvent event) {
		System.out.println("onContentDelete: " + event.getOccuredOn());
	}
}
