package dev.animedia.contentservice.content.application.service.user;

import dev.animedia.contentservice.content.application.dto.content.response.ContentDto;
import dev.animedia.contentservice.content.application.dto.genre.GenreDto;
import dev.animedia.contentservice.content.application.dto.status.StatusDto;
import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.resolver.GenreResolverInterface;
import dev.animedia.contentservice.content.application.resolver.StatusResolverInterface;
import dev.animedia.contentservice.content.application.usecase.user.GetContentListUseCase;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;

import java.util.*;
import java.util.stream.Collectors;

public class GetContentListService implements GetContentListUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final StatusResolverInterface statusResolverInterface;
    private final GenreResolverInterface genreResolverInterface;

    public GetContentListService(
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
    public List<ContentDto> get(List<UUID> contentIdList, String languageCode) {
        List<Content> contentList = contentQueryRepository.find(contentIdList, languageCode);

        Set<String> statusIds = new HashSet<>();
        Set<String> genreIds = new HashSet<>();
        for (Content c : contentList) {
            statusIds.add(c.getStatusId());
            genreIds.addAll(c.getGenreIds());
        }

        Map<String, StatusDto> statusDtoMap = statusResolverInterface.resolve(statusIds)
            .stream()
            .collect(Collectors.toMap(status -> status.id().toString(), status -> status));
        Map<String, GenreDto> genreDtoMap = genreResolverInterface.resolve(genreIds)
            .stream()
            .collect(Collectors.toMap(genre -> genre.id().toString(), genre -> genre));

        return contentList.stream().map(content -> {
            StatusDto statusDto = statusDtoMap.getOrDefault(content.getStatusId(), null);
            Set<GenreDto> genresDto = content.getGenreIds().stream()
                .map(genreId -> genreDtoMap.getOrDefault(genreId, null))
                .collect(Collectors.toSet());

            return contentApplicationMapper.toContentResponseDto(
                content,
                statusDto,
                genresDto
            );
        }).toList();
    }
}