package dev.animedia.contentservice.genre.service;

import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreTranslationPageService {
	Page<GenreTranslationResponseDto> search(String name, Long genreId, List<String> languageCodes, Pageable pageable);
}
