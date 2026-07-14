package dev.animedia.contentservice.genre.infrastracture.event;

import dev.animedia.contentservice.genre.application.event.GenreCreateEvent;
import dev.animedia.contentservice.genre.application.event.GenreDeleteEvent;
import dev.animedia.contentservice.genre.application.event.GenreUpdateEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class GenreEventListener {
	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onGenreCreate(GenreCreateEvent event) {
		System.out.println("onGenreCreate: " + event.getOccuredOn());
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onGenreUpdate(GenreUpdateEvent event) {
		System.out.println("onGenreUpdate: " + event.getOccuredOn());
	}

	@Async
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
	public void onGenreDelete(GenreDeleteEvent event) {
		System.out.println("onGenreDelete: " + event.getOccuredOn());
	}
}
