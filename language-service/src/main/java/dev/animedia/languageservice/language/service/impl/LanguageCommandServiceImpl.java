package dev.animedia.languageservice.language.service.impl;

import dev.animedia.languageservice.language.Language;
import dev.animedia.languageservice.language.LanguageMapper;
import dev.animedia.languageservice.language.repository.LanguageRepository;
import dev.animedia.languageservice.language.dto.LanguageRequestDto;
import dev.animedia.languageservice.language.dto.LanguageResponseDto;
import dev.animedia.languageservice.language.exception.LanguageCodeExistsException;
import dev.animedia.languageservice.language.exception.LanguageCodeNotFoundException;
import dev.animedia.languageservice.language.exception.LanguageNameExistsException;
import dev.animedia.languageservice.language.service.LanguageCommandService;
import dev.animedia.languageservice.language.service.LanguageQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LanguageCommandServiceImpl implements LanguageCommandService {

	private final LanguageRepository languageRepository;
	private final LanguageQueryService languageQueryService;
	private final LanguageMapper languageMapper;

	@Autowired
	public LanguageCommandServiceImpl(
		LanguageRepository languageRepository,
		LanguageQueryService languageQueryService, LanguageMapper languageMapper
	) {
		this.languageRepository = languageRepository;
		this.languageQueryService = languageQueryService;
		this.languageMapper = languageMapper;
	}

	@Override
	public LanguageResponseDto create(LanguageRequestDto requestDto) {

		var languageCodeExists = languageQueryService.existsByCode(requestDto.code());
		if (languageCodeExists) throw new LanguageCodeExistsException();

		var languageNameExists = languageQueryService.existsByCode(requestDto.name());
		if (languageNameExists) throw new LanguageNameExistsException();

		Language language = languageMapper.toLanguage(requestDto);

		var savedLanguage = languageRepository.save(language);

		return languageMapper.toLanguageResponseDto(savedLanguage);
	}

	@Override
	public LanguageResponseDto update(LanguageRequestDto requestDto) {

		var language = languageRepository.findById(requestDto.code()).orElseThrow(LanguageCodeNotFoundException::new);

		var languageNameExists = languageQueryService.existsByNameExcludingId(requestDto.name(), requestDto.code());
		if (languageNameExists) throw new LanguageNameExistsException();

		language.setName(requestDto.name());

		var savedLanguage = languageRepository.save(language);

		return languageMapper.toLanguageResponseDto(savedLanguage);
	}

	@Override
	public void delete(String languageCode) {
		var languageExists = languageQueryService.existsByCode(languageCode);
		if (!languageExists) throw new LanguageCodeNotFoundException();
		languageRepository.deleteById(languageCode);
	}
}
