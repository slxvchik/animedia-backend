package dev.animedia.contentservice.application.status.usecase.translation;

import dev.animedia.contentservice.application.status.dto.StatusTranslationDto;

import java.util.List;

public interface GetStatusTranslationListUseCase {
    List<StatusTranslationDto> getList(List<Long> idList);
}
