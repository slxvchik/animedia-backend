package dev.animedia.contentservice.application.genre.service;

import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.application.genre.usecase.DeleteGenreUseCase;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;

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
	public void delete(Long id) {
		genreQueryRepository.findById(id, false, null)
			.orElseThrow(() -> new GenreNotFoundException(GenreNotFoundException.CODE.GENRE_NOT_FOUND));

		genreCommandRepository.delete(id);
	}
}
