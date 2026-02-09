package dev.animedia.contentservice.genre.service.impl;

import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.repository.GenreTranslationNativeRepository;
import dev.animedia.contentservice.genre.service.GenreTranslationPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreTranslationPageServiceImpl implements GenreTranslationPageService {

	private final GenreTranslationNativeRepository genreTranslationNativeRepository;

	@Autowired
	public GenreTranslationPageServiceImpl(
		GenreTranslationNativeRepository genreTranslationNativeRepository
	) {
		this.genreTranslationNativeRepository = genreTranslationNativeRepository;
	}

	@Override
	public Page<GenreTranslationResponseDto> search(String name, Long genreId, List<String> languageCodes, Pageable pageable) {
		return genreTranslationNativeRepository.searchPage(name, genreId, languageCodes, pageable);
	}
}
