package dev.animedia.contentservice.infrastructure.config.content;

import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.service.*;
import dev.animedia.contentservice.application.content.usecase.*;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.genre.usecase.GetGenreListUseCase;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.application.status.usecase.GetStatusUseCase;
import dev.animedia.contentservice.application.status.usecase.UpdateStatusUseCase;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.domain.content.repository.ContentSearchRepository;
import dev.animedia.contentservice.infrastructure.transactional.content.CreateContentTransactionalDecorator;
import dev.animedia.contentservice.infrastructure.transactional.status.UpdateStatusTransactionalDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ContentUseCaseConfig {
	@Bean("createContentUseCase")
	public CreateContentUseCase createContentUseCase(
		ContentApplicationMapper contentApplicationMapper,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository,
		GetStatusUseCase getStatusUseCase,
		GetGenreListUseCase getGenreListUseCase
	) {
		return new CreateContentService(
			contentApplicationMapper,
			statusApplicationMapper,
			genreApplicationMapper,
			contentQueryRepository,
			contentCommandRepository,
			getStatusUseCase,
			getGenreListUseCase
		);
	}

	@Bean
	@Primary
	public CreateContentUseCase createContentFacade(
		CreateContentUseCase createContentUseCase
	) {
		return new CreateContentTransactionalDecorator(
			createContentUseCase
		);
	}

	@Bean("updateContentUseCase")
	public UpdateContentUseCase updateContentUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		return new UpdateContentService(
			contentApplicationMapper,
			contentQueryRepository,
			contentCommandRepository,
			statusApplicationMapper,
			genreApplicationMapper
		);
	}

	@Bean
	@Primary
	public UpdateStatusUseCase updateStatusFacade(
		UpdateStatusUseCase updateStatusUseCase
	) {
		return new UpdateStatusTransactionalDecorator(
			updateStatusUseCase
		);
	}

	@Bean
	public DeleteContentUseCase deleteContentUseCase(
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository
	) {
		return new DeleteContentService(
			contentQueryRepository,
			contentCommandRepository
		);
	}

	@Bean
	public SaveContentTranslationUseCase saveContentTranslationUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		return new SaveContentTranslationService(
			contentApplicationMapper,
			contentQueryRepository,
			contentCommandRepository,
			statusApplicationMapper,
			genreApplicationMapper
		);
	}

	@Bean
	public DeleteContentTranslationUseCase deleteContentTranslationUseCase(
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository
	) {
		return new DeleteContentTranslationService(
			contentQueryRepository,
			contentCommandRepository
		);
	}

	@Bean
	public GetContentByDetailsUseCase getContentByDetailsUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		return new GetContentByDetailsService(
			contentApplicationMapper,
			contentQueryRepository,
			statusApplicationMapper,
			genreApplicationMapper
		);
	}

	@Bean
	public GetContentByIdUseCase getContentByIdUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		return new GetContentByIdService(
			contentApplicationMapper,
			contentQueryRepository,
			statusApplicationMapper,
			genreApplicationMapper
		);
	}

	@Bean
	public SearchContentUseCase searchContentUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentSearchRepository contentSearchRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		return new SearchContentService(
			contentApplicationMapper,
			contentSearchRepository,
			statusApplicationMapper,
			genreApplicationMapper
		);
	}
}
