package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentSearchDto;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.SearchContentUseCase;
import dev.animedia.contentservice.application.genre.mapper.GenreApplicationMapper;
import dev.animedia.contentservice.application.status.mapper.StatusApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.content.repository.ContentSearchRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;

public class SearchContentService implements SearchContentUseCase {
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentSearchRepository contentSearchRepository;
    private final StatusApplicationMapper statusApplicationMapper;
    private final GenreApplicationMapper genreApplicationMapper;

    public SearchContentService(
        ContentApplicationMapper contentApplicationMapper,
        ContentSearchRepository contentSearchRepository,
        StatusApplicationMapper statusApplicationMapper,
        GenreApplicationMapper genreApplicationMapper
    ) {
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentSearchRepository = contentSearchRepository;
        this.statusApplicationMapper = statusApplicationMapper;
        this.genreApplicationMapper = genreApplicationMapper;
    }

    @Override
    public Page<ContentDto> search(ContentSearchDto contentSearchDto, Pageable pageable) {
        ContentSearchCriteria contentSearchCriteria = contentApplicationMapper.toContentSearchCriteria(contentSearchDto);
        Page<Content> contentPage = contentSearchRepository.search(contentSearchCriteria, pageable);
        return contentPage.changeContent(content -> contentApplicationMapper.toContentDto(
            content,
            statusApplicationMapper::toStatusDto,
            genreApplicationMapper::toGenreDto
        ));
    }
}