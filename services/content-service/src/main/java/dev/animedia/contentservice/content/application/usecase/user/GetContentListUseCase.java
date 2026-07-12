package dev.animedia.contentservice.content.application.usecase.user;

import dev.animedia.contentservice.content.application.dto.content.ContentResponseDto;

import java.util.List;
import java.util.UUID;

public interface GetContentListUseCase {
	List<ContentResponseDto> get(List<UUID> contentIdList, String languageCode);
}