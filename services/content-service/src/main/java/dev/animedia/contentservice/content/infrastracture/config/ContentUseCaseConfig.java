package dev.animedia.contentservice.content.infrastracture.config;

import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.resolver.GenreDomainResolver;
import dev.animedia.contentservice.content.application.resolver.StatusDomainResolver;
import dev.animedia.contentservice.content.application.service.IndexAllContentService;
import dev.animedia.contentservice.content.application.service.admin.CreateContentService;
import dev.animedia.contentservice.content.application.service.admin.DeleteContentService;
import dev.animedia.contentservice.content.application.service.admin.UpdateContentService;
import dev.animedia.contentservice.content.application.service.user.GetContentListService;
import dev.animedia.contentservice.content.application.usecase.IndexAllContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.CreateContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.DeleteContentUseCase;
import dev.animedia.contentservice.content.application.usecase.admin.UpdateContentUseCase;
import dev.animedia.contentservice.content.application.usecase.user.GetContentListUseCase;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.content.application.service.user.GetContentDetailService;
import dev.animedia.contentservice.content.application.usecase.user.GetContentDetailUseCase;
import dev.animedia.contentservice.content.domain.repository.ContentCommandRepository;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.genre.domain.repository.GenreQueryRepository;
import dev.animedia.contentservice.status.domain.repository.StatusQueryRepository;
import dev.animedia.contentservice.content.infrastracture.transactional.CreateContentTransactionalDecorator;
import dev.animedia.contentservice.content.infrastracture.transactional.UpdateContentTransactionalDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ContentUseCaseConfig {
	@Bean("createContentUseCase")
	public CreateContentUseCase createContentUseCase(
		ContentApplicationMapper contentApplicationMapper,
		StatusDomainResolver statusDomainResolver,
		StatusApplicationMapper statusApplicationMapper,
		GenreDomainResolver genreDomainResolver,
		GenreApplicationMapper genreApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository
	) {
		return new CreateContentService(
			contentApplicationMapper,
			statusDomainResolver,
			statusApplicationMapper,
			genreDomainResolver,
			genreApplicationMapper,
			contentQueryRepository,
			contentCommandRepository
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
		StatusDomainResolver statusDomainResolver,
		StatusApplicationMapper statusApplicationMapper,
		GenreDomainResolver genreDomainResolver,
		GenreApplicationMapper genreApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository
	) {
		return new UpdateContentService(
			contentApplicationMapper,
			statusDomainResolver,
			statusApplicationMapper,
			genreDomainResolver,
			genreApplicationMapper,
			contentQueryRepository,
			contentCommandRepository
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
	public StatusDomainResolver statusDomainResolver(
		StatusQueryRepository statusQueryRepository
	) {
		return new StatusDomainResolver(
			statusQueryRepository
		);
	}

	@Bean
	public GenreDomainResolver genreDomainResolver(
		GenreQueryRepository genreQueryRepository
	) {
		return new GenreDomainResolver(
			genreQueryRepository
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
	public dev.animedia.contentservice.content.application.usecase.admin.GetContentDetailUseCase getContentByIdUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		return new dev.animedia.contentservice.content.application.service.admin.GetContentDetailService(
			contentApplicationMapper,
			contentQueryRepository,
			statusApplicationMapper,
			genreApplicationMapper
		);
	}

	@Bean
	public IndexAllContentUseCase getAllContentUseCase(
		ContentApplicationMapper contentApplicationMapper,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper,
		ContentQueryRepository contentQueryRepository
	) {
		return new IndexAllContentService(
			contentApplicationMapper,
			statusApplicationMapper,
			genreApplicationMapper,
			contentQueryRepository
		);
	}

	@Bean
	public GetContentListUseCase getContentListUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		return new GetContentListService(
			contentApplicationMapper,
			contentQueryRepository,
			statusApplicationMapper,
			genreApplicationMapper
		);
	}

	@Bean
	public GetContentDetailUseCase getContentByDetailsUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		return new GetContentDetailService(
			contentApplicationMapper,
			contentQueryRepository,
			statusApplicationMapper,
			genreApplicationMapper
		);
	}
}
