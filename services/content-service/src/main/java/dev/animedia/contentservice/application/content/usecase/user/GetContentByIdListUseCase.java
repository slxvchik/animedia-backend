package dev.animedia.contentservice.application.content.usecase.user;

import dev.animedia.contentservice.application.content.dto.ContentDto;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

public interface GetContentByIdListUseCase {
	List<ContentDto> get(List<UUID> contentIdList, @Nullable String languageCode);
}