package dev.animedia.contentservice.status.application.usecase;

import dev.animedia.contentservice.status.application.dto.StatusDto;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;

public interface IndexAllStatusUseCase {
    Page<StatusDto> index(Pageable pageable);
}
