package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.contentstatus.ContentStatusMapper;
import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusUserSearchRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.exception.ContentStatusNotFoundException;
import dev.animedia.contentservice.contentstatus.mapper.ContentStatusTranslationMapper;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusRepository;
import dev.animedia.contentservice.contentstatus.service.ContentStatusQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentStatusQueryImpl implements ContentStatusQueryService {

    private final ContentStatusRepository contentStatusRepository;
    private final ContentStatusMapper contentStatusMapper;
    private final ContentStatusTranslationMapper contentStatusTranslationMapper;

    @Autowired
    public ContentStatusQueryImpl(
        ContentStatusRepository contentStatusRepository,
        ContentStatusMapper contentStatusMapper,
        ContentStatusTranslationMapper contentStatusTranslationMapper
    ) {
        this.contentStatusRepository = contentStatusRepository;
        this.contentStatusMapper = contentStatusMapper;
        this.contentStatusTranslationMapper = contentStatusTranslationMapper;
    }

    @Override
    public List<ContentStatusWithTranslationResponseDto> search(ContentStatusUserSearchRequestDto searchRequestDto) {
        return contentStatusRepository.search(
            searchRequestDto.languageCode(),
            searchRequestDto.aliases(),
            searchRequestDto.names()
        );
    }

    @Override
    public ContentStatusResponseDto findById(Long id) {
        var contentStatus = contentStatusRepository.findById(id).orElseThrow(ContentStatusNotFoundException::new);
        return contentStatusMapper.toContentStatusResponseDto(contentStatus);
    }

    @Override
    public ContentStatusWithTranslationResponseDto findByIdAndLanguageCode(Long id, String languageCode) {
        return null;
    }

    @Override
    public boolean existsById(Long id) {
        return false;
    }

    @Override
    public boolean existsByAlias(String alias) {
        return false;
    }

    @Override
    public boolean existsByAliasExcludingId(String alias, Long id) {
        return false;
    }
}
