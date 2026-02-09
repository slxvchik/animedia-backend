package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;

public interface ContentStatusCommandService {
	ContentStatusResponseDto create(ContentStatusRequestDto contentStatusRequestDto);
	ContentStatusResponseDto update(UpdateContentStatusRequestDto updateContentStatusRequestDto);
	void delete(Long id);
}
