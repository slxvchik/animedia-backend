package dev.animedia.contentservice.content.application.service;

import dev.animedia.contentservice.content.application.dto.content.response.ContentDto;
import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.resolver.GenreResolverInterface;
import dev.animedia.contentservice.content.application.resolver.StatusResolverInterface;
import dev.animedia.contentservice.content.application.usecase.IndexAllContentUseCase;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class IndexAllContentService implements IndexAllContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final StatusResolverInterface statusResolverInterface;
    private final GenreResolverInterface genreResolverInterface;

    public IndexAllContentService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository,
	    StatusResolverInterface statusResolverInterface,
	    GenreResolverInterface genreResolverInterface
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
	    this.contentQueryRepository = contentQueryRepository;
	    this.statusResolverInterface = statusResolverInterface;
	    this.genreResolverInterface = genreResolverInterface;
    }

    @Override
    public Page<ContentDto> index(Pageable pageable) {
        Page<Content> contentPage = contentQueryRepository.findAll(pageable);

        Set<String> statusIds = new HashSet<>();
        Set<String> genreIds = new HashSet<>();
        for (Content c : contentPage.content()) {
            statusIds.add(c.getStatusId());
            genreIds.addAll(c.getGenreIds());
        }

        Map<String, StatusDto> statusDtoMap = statusResolverInterface.resolve(statusIds)
            .stream()
            .collect(Collectors.toMap(status -> status.id().toString(), status -> status));
        Map<String, GenreDto> genreDtoMap = genreResolverInterface.resolve(genreIds)
            .stream()
            .collect(Collectors.toMap(genre -> genre.id().toString(), genre -> genre));

        return contentPage.changeContent(content -> {
            StatusDto statusDto = statusDtoMap.getOrDefault(content.getStatusId(), null);
            Set<GenreDto> genresDto = content.getGenreIds().stream()
                .map(genreId -> genreDtoMap.getOrDefault(genreId, null))
                .collect(Collectors.toSet());

            return contentApplicationMapper.toContentResponseDto(
                content,
                statusDto,
                genresDto
            );
        });
    }
}