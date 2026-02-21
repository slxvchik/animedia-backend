package dev.animedia.languageservice.service.impl;

import dev.animedia.languageservice.dto.LanguageResponseDto;
import dev.animedia.languageservice.repository.LanguageNativeRepository;
import dev.animedia.languageservice.service.LanguagePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LanguagePageServiceImpl implements LanguagePageService {

	private final LanguageNativeRepository languageNativeRepository;

	@Autowired
	public LanguagePageServiceImpl(LanguageNativeRepository languageNativeRepository) {
		this.languageNativeRepository = languageNativeRepository;
	}

	@Override
	public Page<LanguageResponseDto> search(List<String> languageCodes, List<String> nativeNames, Boolean isActive, Pageable pageable) {
		return languageNativeRepository.searchPage(languageCodes, nativeNames, isActive, pageable);
	}
}
