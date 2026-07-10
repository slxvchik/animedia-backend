package dev.animedia.contentservice.infrastructure.config.content;

import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.resolver.GenreDomainResolver;
import dev.animedia.contentservice.application.content.resolver.StatusDomainResolver;
import dev.animedia.contentservice.application.content.service.admin.*;
import dev.animedia.contentservice.application.content.usecase.admin.*;
import dev.animedia.contentservice.application.content.usecase.user.GetContentByDetailsUseCase;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.domain.genre.repository.GenreQueryRepository;
import dev.animedia.contentservice.domain.status.repository.StatusQueryRepository;
import dev.animedia.contentservice.infrastructure.transactional.content.CreateContentTransactionalDecorator;
import dev.animedia.contentservice.infrastructure.transactional.content.UpdateContentTransactionalDecorator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ContentUseCaseConfig {
	@Bean("createContentUseCase")
	public CreateContentUseCase createContentUseCase(
		ContentApplicationMapper contentApplicationMapper,
		StatusDomainResolver statusDomainResolver,
		GenreDomainResolver genreDomainResolver,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository
	) {
		return new CreateContentService(
			contentApplicationMapper,
			statusDomainResolver,
			genreDomainResolver,
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
		GenreDomainResolver genreDomainResolver,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository
	) {
		return new UpdateContentService(
			contentApplicationMapper,
			statusDomainResolver,
			genreDomainResolver,
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
	public GetContentByDetailsUseCase getContentByDetailsUseCase(
		ContentApplicationMapper contentApplicationMapper,
		ContentQueryRepository contentQueryRepository,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper
	) {
		return new dev.animedia.contentservice.application.content.service.user.GetContentDetailService(
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
		return new GetContentDetailService(
			contentApplicationMapper,
			contentQueryRepository,
			statusApplicationMapper,
			genreApplicationMapper
		);
	}

	@Bean
	public GetAllContentUseCase getAllContentUseCase(
		ContentApplicationMapper contentApplicationMapper,
		StatusApplicationMapper statusApplicationMapper,
		GenreApplicationMapper genreApplicationMapper,
		ContentQueryRepository contentQueryRepository
	) {
		return new GetAllContentService(
			contentApplicationMapper,
			statusApplicationMapper,
			genreApplicationMapper,
			contentQueryRepository
		);
	}
}
