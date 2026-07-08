package dev.animedia.contentservice.application.status.usecase.admin;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;

public interface GetAllStatusUseCase {
    Page<StatusDto> get(Pageable pageable);
}
