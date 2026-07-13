package dev.animedia.contentservice.genre.application.service.admin;

import dev.animedia.contentservice.genre.application.event.GenreDeleteEvent;
import dev.animedia.contentservice.genre.application.exception.GenreNotFoundException;
import dev.animedia.contentservice.genre.application.usecase.admin.DeleteGenreUseCase;
import dev.animedia.contentservice.genre.domain.repository.GenreCommandRepository;
import dev.animedia.contentservice.genre.domain.repository.GenreQueryRepository;
import dev.animedia.contentservice.shared.domain.event.EventDispatcher;

import java.util.UUID;

public class DeleteGenreService implements DeleteGenreUseCase {
	private final GenreQueryRepository genreQueryRepository;
	private final GenreCommandRepository genreCommandRepository;
	private final EventDispatcher eventDispatcher;

	public DeleteGenreService(
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository genreCommandRepository,
		EventDispatcher eventDispatcher
	) {
		this.genreQueryRepository = genreQueryRepository;
		this.genreCommandRepository = genreCommandRepository;
		this.eventDispatcher = eventDispatcher;
	}

	@Override
	public void delete(UUID id) {
		genreQueryRepository.findById(id, null)
			.orElseThrow(GenreNotFoundException::new);
		genreCommandRepository.delete(id);
		eventDispatcher.dispatch(new GenreDeleteEvent(id));
	}
}
