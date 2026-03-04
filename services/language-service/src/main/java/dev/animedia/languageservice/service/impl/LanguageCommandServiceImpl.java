package dev.animedia.languageservice.service.impl;

import dev.animedia.languageservice.constants.LanguageErrorConstants;
import dev.animedia.languageservice.dto.LanguageRequestDto;
import dev.animedia.languageservice.dto.LanguageResponseDto;
import dev.animedia.languageservice.exception.AppException;
import dev.animedia.languageservice.exception.AppExceptionStatus;
import dev.animedia.languageservice.mapper.LanguageMapper;
import dev.animedia.languageservice.model.Language;
import dev.animedia.languageservice.repository.LanguageRepository;
import dev.animedia.languageservice.service.LanguageCommandService;
import dev.animedia.languageservice.service.LanguageQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

		List<String> errorMessages = new ArrayList<>();

		var languageCodeExists = languageQueryService.existsByCode(requestDto.code());
		if (languageCodeExists) errorMessages.add(LanguageErrorConstants.LANGUAGE_CODE_EXISTS_MESSAGE);

		var languageNameExists = languageQueryService.existsByCode(requestDto.name());
		if (languageNameExists) errorMessages.add(LanguageErrorConstants.LANGUAGE_NAME_EXISTS_MESSAGE);

		if (!errorMessages.isEmpty()) throw new AppException(AppExceptionStatus.ALREADY_EXISTS, errorMessages);

		Language language = languageMapper.toLanguage(requestDto);

		var savedLanguage = languageRepository.save(language);

		return languageMapper.toLanguageResponseDto(savedLanguage);
	}

	@Override
	public LanguageResponseDto update(LanguageRequestDto requestDto) {

		var language = languageRepository.findById(requestDto.code())
			.orElseThrow(() -> new AppException(AppExceptionStatus.NOT_FOUND, LanguageErrorConstants.LANGUAGE_CODE_NOT_FOUND_MESSAGE));

		var languageNameExists = languageQueryService.existsByNameExcludingId(requestDto.name(), requestDto.code());
		if (languageNameExists) throw new AppException(AppExceptionStatus.ALREADY_EXISTS, LanguageErrorConstants.LANGUAGE_NAME_EXISTS_MESSAGE);

		language.setName(requestDto.name());

		var savedLanguage = languageRepository.save(language);

		return languageMapper.toLanguageResponseDto(savedLanguage);
	}

	@Override
	public void delete(String languageCode) {
		var languageExists = languageQueryService.existsByCode(languageCode);
		if (!languageExists) throw new AppException(AppExceptionStatus.NOT_FOUND, LanguageErrorConstants.LANGUAGE_CODE_NOT_FOUND_MESSAGE);
		languageRepository.deleteById(languageCode);
	}
}
