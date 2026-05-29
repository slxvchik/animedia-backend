package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.exception.ContentExistsException;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.resolver.GenreDomainResolver;
import dev.animedia.contentservice.application.content.resolver.StatusDomainResolver;
import dev.animedia.contentservice.application.content.usecase.CreateContentUseCase;
import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentCommandRepository;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.status.model.Status;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateContentService implements CreateContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;

	private final StatusDomainResolver statusDomainResolver;
	private final GenreDomainResolver genreDomainResolver;

    private final ContentQueryRepository contentQueryRepository;
    private final ContentCommandRepository contentCommandRepository;

    public CreateContentService(
        ContentApplicationMapper contentApplicationMapper,
	    StatusApplicationMapper statusApplicationMapper,
	    GenreApplicationMapper genreApplicationMapper,
	    StatusDomainResolver statusDomainResolver,
	    GenreDomainResolver genreDomainResolver,
        ContentQueryRepository contentQueryRepository,
        ContentCommandRepository contentCommandRepository
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
	    this.statusApplicationMapper = statusApplicationMapper;
	    this.genreApplicationMapper = genreApplicationMapper;
	    this.statusDomainResolver = statusDomainResolver;
	    this.genreDomainResolver = genreDomainResolver;
	    this.contentQueryRepository = contentQueryRepository;
        this.contentCommandRepository = contentCommandRepository;
    }

    @Override
    public ContentDto create(ContentDto contentDto) {

	    UUID statusId = contentDto.status().id();
	    Status status = statusDomainResolver.resolve(statusId);

	    Set<UUID> requestedGenreIdSet = contentDto.genreSet().stream()
		    .map(GenreDto::id)
		    .collect(Collectors.toSet());
	    Set<Genre> genreSet = genreDomainResolver.resolve(requestedGenreIdSet);

        Content content = contentApplicationMapper.toContent(
            contentDto,
	        status,
	        genreSet
        );

        boolean contentExists = contentQueryRepository.exists(content.getAlias(), content.getType(), content.getSeason());
        if (contentExists) throw new ContentExistsException(content.getAlias(), content.getType(), content.getSeason());

        Content saved = contentCommandRepository.create(content);

	    StatusDto savedStatusDto = statusApplicationMapper.toStatusDto(saved.getStatus());
		Set<GenreDto> savedGenreDtoSet = saved.getGenreSet() != null
	        ? saved.getGenreSet().stream()
				.map(genreApplicationMapper::toGenreDto)
				.collect(Collectors.toSet())
			: Set.of();

        return contentApplicationMapper.toContentDto(
            saved,
	        savedStatusDto,
	        savedGenreDtoSet
        );
    }
}
