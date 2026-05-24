package dev.animedia.contentservice.infrastructure.transactional.genre;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.usecase.UpdateGenreUseCase;
import org.springframework.transaction.annotation.Transactional;

public class UpdateGenreTransactionalDecorator implements UpdateGenreUseCase {
	private final UpdateGenreUseCase updateGenreUseCase;

	public UpdateGenreTransactionalDecorator(UpdateGenreUseCase updateGenreUseCase) {
		this.updateGenreUseCase = updateGenreUseCase;
	}

	@Transactional
	@Override
	public GenreDto update(GenreDto genreDto) {
		return updateGenreUseCase.update(genreDto);
	}
}
