package dev.animedia.contentservice.content.application.service.user;

import dev.animedia.contentservice.content.application.dto.ContentDto;
import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.usecase.user.GetContentListUseCase;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class GetContentListService implements GetContentListUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;

    public GetContentListService(
        ContentApplicationMapper contentApplicationMapper,
        ContentQueryRepository contentQueryRepository,
        StatusApplicationMapper statusApplicationMapper,
        GenreApplicationMapper genreApplicationMapper
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentQueryRepository = contentQueryRepository;
        this.statusApplicationMapper = statusApplicationMapper;
        this.genreApplicationMapper = genreApplicationMapper;
    }

    @Override
    public List<ContentDto> get(List<UUID> contentIdList, String languageCode) {
        List<Content> contentList = contentQueryRepository.find(contentIdList, languageCode);
        return contentList.stream().map(content -> contentApplicationMapper.toContentDto(
            content,
            statusApplicationMapper.toStatusDto(content.getStatus()),
            content.getGenreSet() != null
                ? content.getGenreSet().stream()
                    .map(genreApplicationMapper::toGenreDto)
                    .collect(Collectors.toSet())
                : Set.of()
        )).toList();
    }
}