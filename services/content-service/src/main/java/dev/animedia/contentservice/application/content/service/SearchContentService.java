package dev.animedia.contentservice.application.content.service;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentSearchDto;
import dev.animedia.contentservice.application.content.mapper.ContentApplicationMapper;
import dev.animedia.contentservice.application.content.usecase.SearchContentUseCase;
import dev.animedia.contentservice.application.shared.mapper.PaginationApplicationMapper;
import dev.animedia.contentservice.domain.content.model.Content;
import dev.animedia.contentservice.domain.content.model.ContentSearchCriteria;
import dev.animedia.contentservice.domain.content.repository.ContentSearchRepository;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchContentService implements SearchContentUseCase {
    private final PaginationApplicationMapper paginationApplicationMapper;
    private final ContentApplicationMapper contentApplicationMapper;
    private final ContentSearchRepository contentSearchRepository;

    @Autowired
    public SearchContentService(
        PaginationApplicationMapper paginationApplicationMapper,
        ContentApplicationMapper contentApplicationMapper,
        ContentSearchRepository contentSearchRepository
    ) {
        this.paginationApplicationMapper = paginationApplicationMapper;
        this.contentApplicationMapper = contentApplicationMapper;
        this.contentSearchRepository = contentSearchRepository;
    }

    @Override
    public Page<ContentDto> search(ContentSearchDto contentSearchDto, Pageable pageable) {
        ContentSearchCriteria contentSearchCriteria = contentApplicationMapper.toContentSearchCriteria(contentSearchDto);
        Page<Content> contentPage = contentSearchRepository.search(contentSearchCriteria, pageable);
        return paginationApplicationMapper.changeContent(contentPage, contentApplicationMapper::toContentDto);
    }
}