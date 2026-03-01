package dev.animedia.contentservice.status.service.impl;

import dev.animedia.contentservice.app.exception.AppException;
import dev.animedia.contentservice.status.ContentStatusConstants;
import dev.animedia.contentservice.status.mapper.ContentStatusMapper;
import dev.animedia.contentservice.status.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.repository.ContentStatusRepository;
import dev.animedia.contentservice.status.service.ContentStatusQueryService;
import dev.animedia.contentservice.status.service.ContentStatusTranslationQueryService;
import io.grpc.Status;
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
    public ContentStatusResponseDto findById(Long id) {
        var contentStatus = contentStatusRepository.findById(id)
            .orElseThrow(() -> new AppException(Status.Code.NOT_FOUND, ContentStatusConstants.CONTENT_STATUS_NOT_FOUND_MESSAGE));
        return contentStatusMapper.toContentStatusResponseDto(contentStatus);
    }

    @Override
    public ContentStatusWithTranslationResponseDto findByIdAndLanguageCode(Long id, String languageCode) {
        var contentStatusesResponseDto = contentStatusRepository.findAllByIdAndLanguageCode(List.of(id), languageCode);
        if (contentStatusesResponseDto.isEmpty()) throw new AppException(Status.Code.NOT_FOUND, ContentStatusConstants.CONTENT_STATUS_NOT_FOUND_MESSAGE);
        return contentStatusesResponseDto.getFirst();
    }

    @Override
    public List<ContentStatusWithTranslationResponseDto> findByIdsAndLanguageCode(List<Long> ids, String languageCode) {
        return contentStatusRepository.findAllByIdAndLanguageCode(ids, languageCode);
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