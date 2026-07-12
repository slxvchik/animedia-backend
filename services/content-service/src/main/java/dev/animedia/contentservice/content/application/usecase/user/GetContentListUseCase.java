package dev.animedia.contentservice.content.application.usecase.user;

import dev.animedia.contentservice.content.application.dto.content.ContentRequestDto;

import java.util.List;
import java.util.UUID;

public interface GetContentListUseCase {
	List<ContentRequestDto> get(List<UUID> contentIdList, String languageCode);
}