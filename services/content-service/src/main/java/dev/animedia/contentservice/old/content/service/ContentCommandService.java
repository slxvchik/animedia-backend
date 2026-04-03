package dev.animedia.contentservice.old.content.service;

import dev.animedia.contentservice.old.content.dto.request.ContentRequestDto;
import dev.animedia.contentservice.old.content.dto.response.ContentResponseDto;

import java.util.UUID;

public interface ContentCommandService {
	ContentResponseDto create(ContentRequestDto contentRequestDto, String languageCode);
	ContentResponseDto update(UUID uuid, ContentRequestDto contentRequestDto, String languageCode);
	void delete(UUID uuid);
}
