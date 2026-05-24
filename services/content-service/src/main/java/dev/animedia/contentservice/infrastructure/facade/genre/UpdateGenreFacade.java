package dev.animedia.contentservice.infrastructure.facade.genre;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.usecase.UpdateGenreUseCase;
import org.springframework.transaction.annotation.Transactional;

public class UpdateGenreFacade implements UpdateGenreUseCase {
	private final UpdateGenreUseCase updateGenreUseCase;

	public UpdateGenreFacade(UpdateGenreUseCase updateGenreUseCase) {
		this.updateGenreUseCase = updateGenreUseCase;
	}

	@Transactional
	@Override
	public GenreDto update(GenreDto genreDto) {
		return updateGenreUseCase.update(genreDto);
	}
}
