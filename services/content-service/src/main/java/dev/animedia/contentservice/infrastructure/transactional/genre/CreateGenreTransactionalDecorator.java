package dev.animedia.contentservice.infrastructure.transactional.genre;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.usecase.admin.CreateGenreUseCase;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class CreateGenreTransactionalDecorator implements CreateGenreUseCase {
	private final CreateGenreUseCase createGenreUseCase;

	public CreateGenreTransactionalDecorator(CreateGenreUseCase createGenreUseCase) {
		this.createGenreUseCase = createGenreUseCase;
	}

	@Transactional
	@Override
	public UUID create(GenreDto genreDto) {
		return createGenreUseCase.create(genreDto);
	}
}
