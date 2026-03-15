package dev.animedia.contentservice.content.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.content.dto.response.ContentTranslationResponseDto;
import dev.animedia.contentservice.content.mapper.ContentTranslationMapper;
import dev.animedia.contentservice.content.repository.ContentTranslationRepository;
import dev.animedia.contentservice.content.service.ContentTranslationQueryService;

@Service
public class ContentTranslationQueryServiceImpl implements ContentTranslationQueryService {

	private final ContentTranslationRepository contentTranslationRepository;
	private final ContentTranslationMapper contentTranslationMapper;

	@Autowired
    public ContentTranslationQueryServiceImpl(
		ContentTranslationRepository contentTranslationRepository,
		ContentTranslationMapper contentTranslationMapper
	) {
        this.contentTranslationRepository = contentTranslationRepository;
		this.contentTranslationMapper = contentTranslationMapper;
    }

	@Override
	public Page<ContentTranslationResponseDto> search(UUID contentUuid, String title, Pageable pageable) {
		var translations = contentTranslationRepository.search(contentUuid, title, pageable);
		var translationsResponseDto = contentTranslationMapper.toContentTranslationListResponseDto(translations.getContent());

		return PageableExecutionUtils.getPage(
			translationsResponseDto,
			translations.getPageable(),
			translations::getTotalElements
		);
	}

	@Override
	public ContentTranslationResponseDto findByContentUuidAndLanguageCode(UUID contentUuid, String languageCode) {
		var translation = contentTranslationRepository.findByContentUuidAndLanguageCode(contentUuid, languageCode);
		return contentTranslationMapper.toContentTranslationResponseDto(translation);
	}

	@Override
	public List<ContentTranslationResponseDto> findByContentUuidsAndLanguageCode(List<UUID> contentUuids, String languageCode) {
		var translations = contentTranslationRepository.findByContentUuidsAndLanguageCode(contentUuids, languageCode);
		return contentTranslationMapper.toContentTranslationListResponseDto(translations);
	}

	@Override
	public boolean existsById(UUID uuid) {
		return contentTranslationRepository.existsById(uuid);
	}

	@Override
	public boolean existsByContentIdAndLanguageCode(UUID contentUuid, String languageCode) {
		return contentTranslationRepository.existsByContentIdAndLanguageCode(contentUuid, languageCode);
	}
}
