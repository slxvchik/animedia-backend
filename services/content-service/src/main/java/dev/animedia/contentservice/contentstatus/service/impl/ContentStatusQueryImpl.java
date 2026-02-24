package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.contentstatus.ContentStatusConstants;
import dev.animedia.contentservice.contentstatus.mapper.ContentStatusMapper;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusRepository;
import dev.animedia.contentservice.contentstatus.service.ContentStatusQueryService;
import dev.animedia.contentservice.contentstatus.service.ContentStatusTranslationQueryService;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public ContentStatusResponseDto findById(Long id) {
        var contentStatus = contentStatusRepository.findById(id)
            .orElseThrow(() -> new AppException(Status.Code.NOT_FOUND, ContentStatusConstants.CONTENT_STATUS_NOT_FOUND_MESSAGE));
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