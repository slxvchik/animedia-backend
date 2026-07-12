package dev.animedia.contentservice.genre.infrastracture.transactional;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.usecase.admin.UpdateGenreUseCase;
import org.springframework.transaction.annotation.Transactional;

public class UpdateGenreTransactionalDecorator implements UpdateGenreUseCase {
	private final UpdateGenreUseCase updateGenreUseCase;

	public UpdateGenreTransactionalDecorator(UpdateGenreUseCase updateGenreUseCase) {
		this.updateGenreUseCase = updateGenreUseCase;
	}

	@Transactional
	@Override
	public void update(GenreDto genreDto) {
		updateGenreUseCase.update(genreDto);
	}
}
