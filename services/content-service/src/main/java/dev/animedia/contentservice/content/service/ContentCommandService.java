package dev.animedia.contentservice.content.service;

import dev.animedia.contentservice.content.dto.request.ContentRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;

import java.util.UUID;

public interface ContentCommandService {
	ContentResponseDto create(ContentRequestDto contentRequestDto, String languageCode);
	ContentResponseDto update(UUID uuid, ContentRequestDto contentRequestDto, String languageCode);
	void delete(UUID uuid);
}
