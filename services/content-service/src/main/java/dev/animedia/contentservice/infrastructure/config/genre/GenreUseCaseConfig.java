package dev.animedia.contentservice.infrastructure.config.genre;

import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.service.*;
import dev.animedia.contentservice.application.genre.usecase.*;
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
	public DeleteGenreTranslationUseCase deleteGenreTranslationUseCase(
		GenreQueryRepository genreQueryRepository,
		GenreCommandRepository genreCommandRepository
	) {
		return new DeleteGenreTranslationService(
			genreQueryRepository,
			genreCommandRepository
		);
	}

	@Bean
	public GetGenreListUseCase getGenreListUseCase(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		return new GetGenreListService(
			genreApplicationMapper,
			genreQueryRepository
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

	@Bean
	public SearchGenreUseCase searchGenreUseCase(
		GenreApplicationMapper genreApplicationMapper,
		GenreQueryRepository genreQueryRepository
	) {
		return new SearchGenreService(
			genreApplicationMapper,
			genreQueryRepository
		);
	}
}
