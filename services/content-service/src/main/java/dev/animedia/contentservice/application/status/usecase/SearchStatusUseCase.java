package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.SearchStatusDto;
import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;

public interface SearchStatusUseCase {
    Page<StatusDto> search(SearchStatusDto searchStatusDto, Pageable pageable);
}
