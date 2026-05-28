package dev.animedia.contentservice.application.content.usecase;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.application.content.dto.ContentSearchDto;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;

public interface SearchContentUseCase {
	Page<ContentDto> search(ContentSearchDto contentSearchDto, Pageable pageable);
}