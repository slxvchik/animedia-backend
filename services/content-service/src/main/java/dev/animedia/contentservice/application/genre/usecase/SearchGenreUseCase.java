package dev.animedia.contentservice.application.genre.usecase;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.SearchGenreDto;
import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;

public interface SearchGenreUseCase {
	Page<GenreDto> search(SearchGenreDto searchGenreDto, Pageable pageable);
}