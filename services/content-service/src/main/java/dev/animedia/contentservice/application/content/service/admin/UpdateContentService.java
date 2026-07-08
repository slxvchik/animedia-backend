package dev.animedia.contentservice.application.content.service.admin;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentNotFoundException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.resolver.GenreDomainResolver;
import dev.animedia.contentservice.application.content.resolver.StatusDomainResolver;
import dev.animedia.contentservice.application.content.usecase.admin.UpdateContentUseCase;
import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentUpdate;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.status.model.Status;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class UpdateContentService implements UpdateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;

    private final StatusDomainResolver statusDomainResolver;
    private final GenreDomainResolver genreDomainResolver;

    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

	public UpdateContentService(
		ContentApplicationMapper contentApplicationMapper,
		StatusDomainResolver statusDomainResolver,
		GenreDomainResolver genreDomainResolver,
		ContentQueryRepository contentQueryRepository,
		ContentCommandRepository contentCommandRepository
	) {
		this.contentApplicationMapper = contentApplicationMapper;
		this.statusDomainResolver = statusDomainResolver;
		this.genreDomainResolver = genreDomainResolver;
		this.contentQueryRepository = contentQueryRepository;
		this.contentCommandRepository = contentCommandRepository;
	}

	@Override
    public void update(ContentDto contentDto) {
        Content content = contentQueryRepository.find(contentDto.id(), null)
            .orElseThrow(() -> new ContentNotFoundException(contentDto.id()));

        UUID statusId = contentDto.status().id();
        Status status = statusDomainResolver.resolve(statusId);

        Set<UUID> requestedGenreIdSet = contentDto.genreSet().stream()
            .map(GenreDto::id)
            .collect(Collectors.toSet());
        Set<Genre> genreSet = genreDomainResolver.resolve(requestedGenreIdSet);

        ContentUpdate contentUpdate = contentApplicationMapper.toContentUpdate(
            contentDto,
            status,
            genreSet
        );
        content.update(contentUpdate);
        contentCommandRepository.update(content);
    }
}
