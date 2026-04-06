package dev.animedia.contentservice.application.status.usecase;

import dev.animedia.contentservice.application.status.dto.StatusDto;
import jakarta.annotation.Nullable;

import java.util.List;

public interface GetStatusListUseCase {
    List<StatusDto> getList(List<Long> idList, @Nullable String languageCode);
}
