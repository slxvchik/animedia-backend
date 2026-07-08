package dev.animedia.contentservice.application.content.usecase.admin;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;

public interface GetAllContentUseCase {
	Page<ContentDto> get(Pageable pageable);
}