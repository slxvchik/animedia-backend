package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusRepository;
import dev.animedia.contentservice.contentstatus.service.ContentStatusPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentStatusPageServiceImpl implements ContentStatusPageService {

	private final ContentStatusRepository contentStatusRepository;

	@Autowired
	public ContentStatusPageServiceImpl(ContentStatusRepository contentStatusRepository) {
		this.contentStatusRepository = contentStatusRepository;
	}

	@Override
	public Page<ContentStatusWithTranslationsResponseDto> search(
		List<Long> contentStatusIds,
		List<String> languageCodes,
		List<String> aliases,
		List<String> names,
		Pageable pageable
	) {
		return contentStatusRepository.search(
			contentStatusIds,
			languageCodes,
			aliases,
			names,
			pageable
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
			languageCode,
			aliases,
			names,
			pageable
		);
	}
}
