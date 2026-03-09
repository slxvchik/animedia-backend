package dev.animedia.contentservice.status.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.contentservice.status.mapper.ContentStatusMapper;
import dev.animedia.contentservice.status.repository.ContentStatusRepository;
import dev.animedia.contentservice.status.service.ContentStatusPageService;

@Service
public class ContentStatusPageServiceImpl implements ContentStatusPageService {

	private final ContentStatusRepository contentStatusRepository;
	private final ContentStatusMapper contentStatusMapper;

	@Autowired
	public ContentStatusPageServiceImpl(ContentStatusRepository contentStatusRepository,
		ContentStatusMapper contentStatusMapper
	) {
		this.contentStatusRepository = contentStatusRepository;
		this.contentStatusMapper = contentStatusMapper;
	}

	@Override
	public Page<ContentStatusWithTranslationsResponseDto> search(
		List<Long> contentStatusIds,
		List<String> languageCodes,
		String alias,
		String name,
		Pageable pageable
	) {
		var contentStatusesWithTranslation = contentStatusRepository.search(
			contentStatusIds,
			languageCodes,
			alias,
			name,
			pageable
		);

		var contentStatusesWithTranslations = contentStatusMapper.toContentStatusesWithTranslations(contentStatusesWithTranslation.getContent());

		return PageableExecutionUtils.getPage(
			contentStatusesWithTranslations,
			contentStatusesWithTranslation.getPageable(),
			contentStatusesWithTranslation::getTotalElements
		);
	}

	@Override
	public Page<ContentStatusWithTranslationResponseDto> search(
		String languageCode,
		String alias,
		String name,
		Pageable pageable
	) {
		return contentStatusRepository.search(
			null,
			List.of(languageCode),
			alias,
			name,
			pageable
		);
	}
}
