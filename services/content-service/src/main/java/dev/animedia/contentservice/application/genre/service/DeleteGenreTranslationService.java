package dev.animedia.contentservice.application.genre.service;

import dev.animedia.contentservice.application.genre.exception.GenreNotFoundException;
import dev.animedia.contentservice.application.genre.usecase.DeleteGenreTranslationUseCase;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteGenreTranslationService implements DeleteGenreTranslationUseCase {
	private final GenreQueryRepository genreQueryRepository;
	private final GenreCommandRepository genreCommandRepository;

	@Autowired
	public DeleteGenreTranslationService(
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository genreCommandRepository
	) {
		this.genreQueryRepository = genreQueryRepository;
		this.genreCommandRepository = genreCommandRepository;
	}

	@Override
	public void deleteTranslation(Long genreId, Long genreTranslationId) {
		Genre genre = genreQueryRepository.findById(genreId, null)
			.orElseThrow(GenreNotFoundException::new);

		genre.removeTranslation(genreTranslationId);

		genreCommandRepository.update(genre);
	}
}