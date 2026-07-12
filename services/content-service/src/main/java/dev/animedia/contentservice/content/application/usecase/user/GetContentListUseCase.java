package dev.animedia.contentservice.content.application.usecase.user;

import dev.animedia.contentservice.content.application.dto.ContentDto;

import java.util.List;
import java.util.UUID;

public interface GetContentListUseCase {
	List<ContentDto> get(List<UUID> contentIdList, String languageCode);
}