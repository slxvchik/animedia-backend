package dev.animedia.languageservice.service;

import dev.animedia.languageservice.dto.LanguageResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface LanguagePageService {
	Page<LanguageResponseDto> search(List<String> languageCodes, List<String> nativeNames, Boolean isActive, Pageable pageable);
}
