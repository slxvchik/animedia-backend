package dev.animedia.contentservice.status.service.impl;

import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.status.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.contentservice.status.mapper.ContentStatusMapper;
import dev.animedia.contentservice.status.repository.ContentStatusRepository;
import dev.animedia.contentservice.status.service.ContentStatusPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

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
		List<String> aliases,
		List<String> names,
		Pageable pageable
	) {
		var contentStatusesWithTranslation = contentStatusRepository.search(
			contentStatusIds,
			languageCodes,
			aliases,
			names,
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
		List<String> aliases,
		List<String> names,
		Pageable pageable
	) {
		return contentStatusRepository.search(
			null,
			List.of(languageCode),
			aliases,
			names,
			pageable
		);
	}
}
