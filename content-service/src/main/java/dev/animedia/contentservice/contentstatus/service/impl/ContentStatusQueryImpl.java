package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.contentstatus.mapper.ContentStatusMapper;
import dev.animedia.contentservice.contentstatus.dto.request.SearchContentStatusUserRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.exception.ContentStatusNotFoundException;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusRepository;
import dev.animedia.contentservice.contentstatus.service.ContentStatusQueryService;
import dev.animedia.contentservice.contentstatus.service.ContentStatusTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentStatusQueryImpl implements ContentStatusQueryService {

    private final ContentStatusRepository contentStatusRepository;
    private final ContentStatusMapper contentStatusMapper;
    private final ContentStatusTranslationQueryService contentStatusTranslationQueryService;

    @Autowired
    public ContentStatusQueryImpl(
        ContentStatusRepository contentStatusRepository,
        ContentStatusMapper contentStatusMapper,
        ContentStatusTranslationQueryService contentStatusTranslationQueryService
    ) {
        this.contentStatusRepository = contentStatusRepository;
        this.contentStatusMapper = contentStatusMapper;
        this.contentStatusTranslationQueryService = contentStatusTranslationQueryService;
    }

    @Override
    public List<ContentStatusWithTranslationResponseDto> search(SearchContentStatusUserRequestDto searchRequestDto) {
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
        var contentStatusResponseDto = this.findById(id);
        var contentStatusTranslationResponseDto = contentStatusTranslationQueryService.findByContentStatusIdAndLanguageCode(id, languageCode);
        return contentStatusMapper.toContentStatusWithTranslationResponseDto(contentStatusResponseDto, contentStatusTranslationResponseDto);
    }

    @Override
    public boolean existsById(Long id) {
        return contentStatusRepository.existsById(id);
    }

    @Override
    public boolean existsByAlias(String alias) {
        return contentStatusRepository.existsByAlias(alias);
    }

    @Override
    public boolean existsByAliasExcludingId(String alias, Long id) {
        return contentStatusRepository.existsByAliasAndIdIsNot(alias, id);
    }
}