package dev.animedia.contentservice.application.genre.service.admin;

import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.application.genre.usecase.admin.DeleteGenreUseCase;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;

import java.util.UUID;

public class DeleteGenreService implements DeleteGenreUseCase {
	private final GenreQueryRepository genreQueryRepository;
	private final GenreCommandRepository genreCommandRepository;

	public DeleteGenreService(
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository genreCommandRepository
	) {
		this.genreQueryRepository = genreQueryRepository;
		this.genreCommandRepository = genreCommandRepository;
	}

	@Override
	public void delete(UUID id) {
		genreQueryRepository.findById(id, null, null)
			.orElseThrow(GenreNotFoundException::new);

		genreCommandRepository.delete(id);
	}
}
