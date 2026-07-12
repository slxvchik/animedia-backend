package dev.animedia.contentservice.content.application.usecase;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;

public interface IndexAllContentUseCase {
	Page<ContentRequestDto> index(Pageable pageable);
}