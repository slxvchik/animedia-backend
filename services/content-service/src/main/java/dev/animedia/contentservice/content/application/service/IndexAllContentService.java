package dev.animedia.contentservice.content.application.service;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;
import dev.animedia.contentservice.content.application.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.content.application.usecase.IndexAllContentUseCase;
import dev.animedia.contentservice.genre.application.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.status.application.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.content.domain.model.Content;
import dev.animedia.contentservice.content.domain.repository.ContentQueryRepository;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;

import java.util.Set;
import java.util.stream.Collectors;

public class IndexAllContentService implements IndexAllContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;
    private final ContentQueryRepository contentQueryRepository;

    public IndexAllContentService(
        ContentApplicationMapper contentApplicationMapper,
        StatusApplicationMapper statusApplicationMapper,
        GenreApplicationMapper genreApplicationMapper,
        ContentQueryRepository contentQueryRepository
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.statusApplicationMapper = statusApplicationMapper;
        this.genreApplicationMapper = genreApplicationMapper;
	    this.contentQueryRepository = contentQueryRepository;
    }

    @Override
    public Page<ContentRequestDto> index(Pageable pageable) {
        Page<Content> contentPage = contentQueryRepository.findAll(pageable);
        return contentPage.changeContent(content -> contentApplicationMapper.toContentResponseDto(
            content,
            statusApplicationMapper.toStatusDto(content.getStatusId()),
            content.getGenreIdSet() != null
                ? content.getGenreIdSet().stream()
                    .map(genreApplicationMapper::toGenreDto)
                    .collect(Collectors.toSet())
                : Set.of()
        ));
    }
}