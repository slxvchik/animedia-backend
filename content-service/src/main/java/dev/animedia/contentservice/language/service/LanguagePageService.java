package dev.animedia.contentservice.language.service;

import dev.animedia.contentservice.language.dto.LanguageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LanguagePageService {
	Page<LanguageResponseDto> search(List<String> languageCodes, List<String> names, Pageable pageable);
}
