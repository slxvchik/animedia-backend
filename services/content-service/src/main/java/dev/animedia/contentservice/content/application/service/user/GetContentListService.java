package dev.animedia.contentservice.content.application.service.user;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;
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
    public List<ContentResponseDto> get(List<UUID> contentIdList, String languageCode) {
        List<Content> contentList = contentQueryRepository.find(contentIdList, languageCode);

        Set<String> statusIdSet = new HashSet<>();
        Set<String> genreIdSet = new HashSet<>();
        for (Content c : contentList) {
            statusIdSet.add(c.getStatusId());
            genreIdSet.addAll(c.getGenreIdSet());
        }

        Map<String, StatusDto> statusDtoMap = statusResolverInterface.resolve(statusIdSet)
            .stream()
            .collect(Collectors.toMap(status -> status.id().toString(), status -> status));
        Map<String, GenreDto> genreDtoMap = genreResolverInterface.resolve(genreIdSet)
            .stream()
            .collect(Collectors.toMap(genre -> genre.id().toString(), genre -> genre));

        return contentList.stream().map(content -> {
            StatusDto statusDto = statusDtoMap.getOrDefault(content.getStatusId(), null);
            Set<GenreDto> genreDtoSet = content.getGenreIdSet().stream()
                .map(genreId -> genreDtoMap.getOrDefault(genreId, null))
                .collect(Collectors.toSet());

            return contentApplicationMapper.toContentResponseDto(
                content,
                statusDto,
                genreDtoSet
            );
        }).toList();
    }
}