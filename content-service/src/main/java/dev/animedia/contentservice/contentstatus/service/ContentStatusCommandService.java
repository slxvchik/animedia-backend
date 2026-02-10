package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;

public interface ContentStatusCommandService {
	ContentStatusResponseDto create(ContentStatusRequestDto contentStatusRequestDto);
	ContentStatusResponseDto update(Long id, ContentStatusRequestDto contentStatusRequestDto);
	void delete(Long id);
}
