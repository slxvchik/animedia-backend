package dev.animedia.contentservice.contentstatus.service.impl;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusAdminSearchRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusUserSearchRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.contentservice.contentstatus.model.ContentStatus;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusRepository;
import dev.animedia.contentservice.contentstatus.service.ContentStatusPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ContentStatusPageServiceImpl implements ContentStatusPageService {

	private final ContentStatusRepository contentStatusRepository;

	@Autowired
	public ContentStatusPageServiceImpl(ContentStatusRepository contentStatusRepository) {
		this.contentStatusRepository = contentStatusRepository;
	}

	@Override
	public Page<ContentStatusWithTranslationsResponseDto> search(
		ContentStatusAdminSearchRequestDto contentStatusAdminSearchRequestDto
	) {
		return contentStatusRepository.search(
			contentStatusAdminSearchRequestDto.contentStatusIds(),
			contentStatusAdminSearchRequestDto.aliases(),
			contentStatusAdminSearchRequestDto.languageCodes(),
			contentStatusAdminSearchRequestDto.names(),
			contentStatusAdminSearchRequestDto.pageable()
		);
	}
}
