package dev.animedia.contentservice.language.service.impl;

import dev.animedia.contentservice.language.dto.LanguageResponseDto;
import dev.animedia.contentservice.language.repository.LanguageNativeRepository;
import dev.animedia.contentservice.language.service.LanguagePageService;
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
	public Page<LanguageResponseDto> search(List<String> languageCodes, List<String> names, Pageable pageable) {
		return languageNativeRepository.searchPage(languageCodes, names, pageable);
	}
}
