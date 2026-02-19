package dev.animedia.languageservice.language.service.impl;

import dev.animedia.languageservice.app.exception.AppException;
import dev.animedia.languageservice.language.Language;
import dev.animedia.languageservice.language.LanguageErrorConstants;
import dev.animedia.languageservice.language.LanguageMapper;
import dev.animedia.languageservice.language.repository.LanguageRepository;
import dev.animedia.languageservice.language.dto.LanguageRequestDto;
import dev.animedia.languageservice.language.dto.LanguageResponseDto;
import dev.animedia.languageservice.language.service.LanguageCommandService;
import dev.animedia.languageservice.language.service.LanguageQueryService;
import io.grpc.Status;
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

		if (!errorMessages.isEmpty()) throw new AppException(Status.Code.ALREADY_EXISTS, errorMessages);

		Language language = languageMapper.toLanguage(requestDto);

		var savedLanguage = languageRepository.save(language);

		return languageMapper.toLanguageResponseDto(savedLanguage);
	}

	@Override
	public LanguageResponseDto update(LanguageRequestDto requestDto) {

		var language = languageRepository.findById(requestDto.code())
			.orElseThrow(() -> new AppException(Status.Code.NOT_FOUND, LanguageErrorConstants.LANGUAGE_CODE_NOT_FOUND_MESSAGE));

		var languageNameExists = languageQueryService.existsByNameExcludingId(requestDto.name(), requestDto.code());
		if (languageNameExists) throw new AppException(Status.Code.ALREADY_EXISTS, LanguageErrorConstants.LANGUAGE_NAME_EXISTS_MESSAGE);

		language.setName(requestDto.name());

		var savedLanguage = languageRepository.save(language);

		return languageMapper.toLanguageResponseDto(savedLanguage);
	}

	@Override
	public void delete(String languageCode) {
		var languageExists = languageQueryService.existsByCode(languageCode);
		if (!languageExists) throw new AppException(Status.Code.NOT_FOUND, LanguageErrorConstants.LANGUAGE_CODE_NOT_FOUND_MESSAGE);
		languageRepository.deleteById(languageCode);
	}
}
