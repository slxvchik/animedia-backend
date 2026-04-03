package dev.animedia.contentservice.old.genre.service;

import dev.animedia.contentservice.old.genre.dto.response.GenreTranslationResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GenreTranslationPageService {
	Page<GenreTranslationResponseDto> search(Long genreId, List<String> names, List<String> languageCodes, Pageable pageable);
}
