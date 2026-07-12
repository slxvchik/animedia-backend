package dev.animedia.contentservice.genre.infrastracture.config;

import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.genre.application.service.IndexAllGenreService;
import dev.animedia.contentservice.genre.application.service.admin.CreateGenreService;
import dev.animedia.contentservice.genre.application.service.admin.DeleteGenreService;
import dev.animedia.contentservice.genre.application.service.admin.GetGenreDetailService;
import dev.animedia.contentservice.genre.application.service.admin.UpdateGenreService;
import dev.animedia.contentservice.genre.application.usecase.IndexAllGenreUseCase;
import dev.animedia.contentservice.genre.domain.repository.GenreCommandRepository;
import dev.animedia.contentservice.genre.domain.repository.GenreQueryRepository;
import dev.animedia.contentservice.genre.application.usecase.admin.CreateGenreUseCase;
import dev.animedia.contentservice.genre.application.usecase.admin.DeleteGenreUseCase;
import dev.animedia.contentservice.genre.application.usecase.admin.GetGenreDetailUseCase;
import dev.animedia.contentservice.genre.application.usecase.admin.UpdateGenreUseCase;
import dev.animedia.contentservice.genre.infrastracture.transactional.CreateGenreTransactionalDecorator;
import dev.animedia.contentservice.genre.infrastracture.transactional.UpdateGenreTransactionalDecorator;
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
	public GetGenreDetailUseCase getGenreUseCase(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		return new GetGenreDetailService(
			genreApplicationMapper,
			genreQueryRepository
		);
	}

	@Bean
	public IndexAllGenreUseCase getAllGenreUseCase(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		return new IndexAllGenreService(
			genreApplicationMapper,
			genreQueryRepository
		);
	}
}
