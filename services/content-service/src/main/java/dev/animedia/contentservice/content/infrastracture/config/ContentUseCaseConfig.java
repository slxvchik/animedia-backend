package dev.animedia.contentservice.content.infrastracture.config;

import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.resolver.GenreResolverInterface;
import dev.animedia.contentservice.content.application.resolver.StatusResolverInterface;
import dev.animedia.contentservice.content.application.service.IndexAllContentService;
import dev.animedia.contentservice.content.application.service.admin.CreateContentService;
import dev.animedia.contentservice.content.application.service.admin.DeleteContentService;
import dev.animedia.contentservice.content.application.service.admin.UpdateContentService;
import dev.animedia.contentservice.content.application.service.user.GetContentDetailService;
import dev.animedia.contentservice.content.application.service.user.GetContentListService;
import dev.animedia.contentservice.content.application.usecase.IndexAllContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.CreateContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.DeleteContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.UpdateContentUseCase;
import dev.animedia.contentservice.content.application.usecase.user.GetContentDetailUseCase;
import dev.animedia.contentservice.content.application.usecase.user.GetContentListUseCase;
import dev.animedia.contentservice.content.domain.repository.ContentCommandRepository;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.content.infrastracture.resolver.genre.GenreResolver;
import dev.animedia.contentservice.content.infrastracture.resolver.genre.GenreResolverMapper;
import dev.animedia.contentservice.content.infrastracture.resolver.status.StatusResolver;
import dev.animedia.contentservice.content.infrastracture.resolver.status.StatusResolverMapper;
import dev.animedia.contentservice.content.infrastracture.transactional.CreateContentTransactionalDecorator;
import dev.animedia.contentservice.content.infrastracture.transactional.UpdateContentTransactionalDecorator;
import dev.animedia.contentservice.genre.application.usecase.GetGenreListUseCase;
import dev.animedia.contentservice.shared.domain.event.EventDispatcher;
import dev.animedia.contentservice.status.application.usecase.GetStatusListUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ContentUseCaseConfig {
	@Bean("createContentUseCase")
	public CreateContentUseCase createContentUseCase(
		ContentApplicationMapper contentApplicationMapper,
		StatusResolverInterface statusResolverInterface,
		GenreResolverInterface genreResolverInterface,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository,
		EventDispatcher eventDispatcher
	) {
		return new CreateContentService(
			contentApplicationMapper,
			statusResolverInterface,
			genreResolverInterface,
			contentQueryRepository,
			contentCommandRepository,
			eventDispatcher
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
		StatusResolverInterface statusResolverInterface,
		GenreResolverInterface genreResolverInterface,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository,
		EventDispatcher eventDispatcher
	) {
		return new UpdateContentService(
			contentApplicationMapper,
			statusResolverInterface,
			genreResolverInterface,
			contentQueryRepository,
			contentCommandRepository,
			eventDispatcher
		);
	}

	@Bean
	@Primary
	public UpdateContentUseCase updateContentFacade(
		UpdateContentUseCase updateContentUseCase
	) {
		return new UpdateContentTransactionalDecorator(
			updateContentUseCase
		);
	}

	@Bean
	public StatusResolverInterface statusResolverInterface(
		GetStatusListUseCase getStatusListUseCase,
		StatusResolverMapper statusResolverMapper
	) {
		return new StatusResolver(
			getStatusListUseCase,
			statusResolverMapper
		);
	}

	@Bean
	public GenreResolverInterface genreResolverInterface(
		GetGenreListUseCase getGenreListUseCase,
		GenreResolverMapper genreResolverMapper
	) {
		return new GenreResolver(
			getGenreListUseCase,
			genreResolverMapper
		);
	}

	@Bean
	public DeleteContentUseCase deleteContentUseCase(
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository,
		EventDispatcher eventDispatcher
	) {
		return new DeleteContentService(
			contentQueryRepository,
			contentCommandRepository,
			eventDispatcher
		);
	}

	@Bean
	public dev.animedia.contentservice.content.application.usecase.admin.GetContentDetailUseCase getContentByIdUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusResolverInterface statusResolverInterface,
		GenreResolverInterface genreResolverInterface
	) {
		return new dev.animedia.contentservice.content.application.service.admin.GetContentDetailService(
			contentApplicationMapper,
			contentQueryRepository,
			statusResolverInterface,
			genreResolverInterface
		);
	}

	@Bean
	public IndexAllContentUseCase indexAllContentUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusResolverInterface statusResolverInterface,
		GenreResolverInterface genreResolverInterface
	) {
		return new IndexAllContentService(
			contentApplicationMapper,
			contentQueryRepository,
			statusResolverInterface,
			genreResolverInterface
		);
	}

	@Bean
	public GetContentListUseCase getContentListUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusResolverInterface statusResolverInterface,
		GenreResolverInterface genreResolverInterface
	) {
		return new GetContentListService(
			contentApplicationMapper,
			contentQueryRepository,
			statusResolverInterface,
			genreResolverInterface
		);
	}

	@Bean
	public GetContentDetailUseCase getContentByDetailsUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusResolverInterface statusResolverInterface,
		GenreResolverInterface genreResolverInterface
	) {
		return new GetContentDetailService(
			contentApplicationMapper,
			contentQueryRepository,
			statusResolverInterface,
			genreResolverInterface
		);
	}
}
