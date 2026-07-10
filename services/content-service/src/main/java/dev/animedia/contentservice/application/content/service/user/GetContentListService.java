package dev.animedia.contentservice.application.content.service.user;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.user.GetContentByIdListUseCase;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.repository.ContentQueryRepository;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class GetContentListService implements GetContentByIdListUseCase {
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
    public List<ContentDto> get(List<UUID> contentIdList, @Nullable String languageCode) {
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