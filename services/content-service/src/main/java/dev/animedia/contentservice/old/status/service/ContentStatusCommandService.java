package dev.animedia.contentservice.old.status.service;

import dev.animedia.contentservice.old.status.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.old.status.dto.response.ContentStatusResponseDto;

public interface ContentStatusCommandService {
	ContentStatusResponseDto create(ContentStatusRequestDto contentStatusRequestDto);
	ContentStatusResponseDto update(Long id, ContentStatusRequestDto contentStatusRequestDto);
	void delete(Long id);
}
