package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.exception.ContentStatusTranslationNotFoundException;
import dev.animedia.contentservice.contentstatus.exception.ContentStatusTranslationsNotFoundException;
import dev.animedia.contentservice.contentstatus.mapper.ContentStatusTranslationMapper;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusTranslationRepository;
import dev.animedia.contentservice.contentstatus.service.ContentStatusTranslationQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ContentStatusTranslationQueryServiceImpl implements ContentStatusTranslationQueryService {

    private final ContentStatusTranslationRepository contentStatusTranslationRepository;
    private final ContentStatusTranslationMapper contentStatusTranslationMapper;

    @Autowired
    public ContentStatusTranslationQueryServiceImpl(
        ContentStatusTranslationRepository contentStatusTranslationRepository,
        ContentStatusTranslationMapper contentStatusTranslationMapper
    ) {
        this.contentStatusTranslationRepository = contentStatusTranslationRepository;
        this.contentStatusTranslationMapper = contentStatusTranslationMapper;
    }

    @Override
    public ContentStatusTranslationResponseDto findById(Long id) {
        var contentStatusTranslation = contentStatusTranslationRepository.findById(id)
            .orElseThrow(ContentStatusTranslationNotFoundException::new);
        return contentStatusTranslationMapper.toContentStatusTranslationResponseDto(contentStatusTranslation);
    }

    @Override
    public ContentStatusTranslationResponseDto findByContentStatusIdAndLanguageCode(Long contentStatusId, String languageCode) {
        var contentStatusTranslation = contentStatusTranslationRepository.findByContentStatusIdAndLanguageCode(contentStatusId, languageCode);
        return contentStatusTranslationMapper.toContentStatusTranslationResponseDto(contentStatusTranslation);
    }

    @Override
    public List<ContentStatusTranslationResponseDto> findByIds(List<Long> ids) {
        Set<Long> uniqueIds = new HashSet<>(ids);
        var contentStatusTranslations = contentStatusTranslationRepository.findAllById(uniqueIds);
        if (contentStatusTranslations.size() != uniqueIds.size()) throw new ContentStatusTranslationsNotFoundException();
        return contentStatusTranslationMapper.toContentStatusTranslationsResponseDto(contentStatusTranslations);
    }

    @Override
    public boolean existsById(Long id) {
        return contentStatusTranslationRepository.existsById(id);
    }

    @Override
    public boolean existsByContentStatusIdAndLanguageCode(Long contentStatusId, String languageCode) {
        return contentStatusTranslationRepository.existsByContentStatusIdAndLanguageCode(contentStatusId, languageCode);
    }
}
