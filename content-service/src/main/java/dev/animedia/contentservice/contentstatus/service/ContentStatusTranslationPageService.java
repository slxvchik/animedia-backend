package dev.animedia.contentservice.contentstatus.service;

import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContentStatusTranslationPageService {
	Page<ContentStatusTranslationResponseDto> search(List<Long> contentStatusIds, List<String> languageCodes, List<String> names);
}
