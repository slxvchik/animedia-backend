package dev.animedia.contentservice.infrastructure.config.genre;

import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.service.admin.CreateGenreService;
import dev.animedia.contentservice.application.genre.service.admin.DeleteGenreService;
import dev.animedia.contentservice.application.genre.service.admin.GetGenreService;
import dev.animedia.contentservice.application.genre.service.admin.UpdateGenreService;
import dev.animedia.contentservice.application.genre.usecase.admin.CreateGenreUseCase;
import dev.animedia.contentservice.application.genre.usecase.admin.DeleteGenreUseCase;
import dev.animedia.contentservice.application.genre.usecase.admin.GetGenreUseCase;
import dev.animedia.contentservice.application.genre.usecase.admin.UpdateGenreUseCase;
import dev.animedia.contentservice.domain.genre.repository.GenreCommandRepository;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import dev.animedia.contentservice.infrastructure.transactional.genre.CreateGenreTransactionalDecorator;
import dev.animedia.contentservice.infrastructure.transactional.genre.UpdateGenreTransactionalDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class GenreUseCaseConfig {
	@Bean("createGenreUseCase")
	public CreateGenreUseCase createGenreUseCase(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository commandRepository
	) {
		return new CreateGenreService(
			genreApplicationMapper,
			genreQueryRepository,
			commandRepository
		);
	}

	@Bean
	@Primary
	public CreateGenreUseCase createGenreFacade(
		CreateGenreUseCase createGenreUseCase
	) {
		return new CreateGenreTransactionalDecorator(
			createGenreUseCase
		);
	}

	@Bean("updateGenreUseCase")
	public UpdateGenreUseCase updateGenreUseCase(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository genreCommandRepository
	) {
		return new UpdateGenreService(
			genreApplicationMapper,
			genreQueryRepository,
			genreCommandRepository
		);
	}

	@Bean
	@Primary
	public UpdateGenreUseCase updateGenreFacade(
		UpdateGenreUseCase updateGenreUseCase
	) {
		return new UpdateGenreTransactionalDecorator(
			updateGenreUseCase
		);
	}

	@Bean
	public DeleteGenreUseCase deleteGenreUseCase(
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository genreCommandRepository
	) {
		return new DeleteGenreService(
			genreQueryRepository,
			genreCommandRepository
		);
	}

	@Bean
	public GetGenreUseCase getGenreUseCase(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		return new GetGenreService(
			genreApplicationMapper,
			genreQueryRepository
		);
	}
}
