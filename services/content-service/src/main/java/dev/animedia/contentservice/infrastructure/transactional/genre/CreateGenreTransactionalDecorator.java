package dev.animedia.contentservice.infrastructure.transactional.genre;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.usecase.CreateGenreUseCase;
import org.springframework.transaction.annotation.Transactional;

public class CreateGenreTransactionalDecorator implements CreateGenreUseCase {
	private final CreateGenreUseCase createGenreUseCase;

	public CreateGenreTransactionalDecorator(CreateGenreUseCase createGenreUseCase) {
		this.createGenreUseCase = createGenreUseCase;
	}

	@Transactional
	@Override
	public GenreDto create(GenreDto genreDto) {
		return createGenreUseCase.create(genreDto);
	}
}
