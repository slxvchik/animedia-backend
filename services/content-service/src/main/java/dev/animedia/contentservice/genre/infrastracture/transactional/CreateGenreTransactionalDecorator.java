package dev.animedia.contentservice.genre.infrastracture.transactional;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.usecase.admin.CreateGenreUseCase;
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
