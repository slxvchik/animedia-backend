package dev.animedia.contentservice.application.status.usecase.translation;

import dev.animedia.contentservice.application.status.dto.SearchStatusTranslationDto;
import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;
import dev.animedia.contentservice.domain.shared.model.Page;

public interface SearchStatusTranslationUseCase {
    Page<StatusTranslationDto> search(SearchStatusTranslationDto searchStatusTranslationDto);
}
