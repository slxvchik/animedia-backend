package dev.animedia.contentservice.application.content.usecase.user;

import dev.animedia.contentservice.application.content.dto.ContentDto;

import java.util.List;
import java.util.UUID;

public interface GetContentByIdListUseCase {
	List<ContentDto> get(List<UUID> contentIdList);
}