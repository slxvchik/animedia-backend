package dev.animedia.contentservice.application.genre.usecase;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreSearchDto;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;

public interface SearchGenreUseCase {
	Page<GenreDto> search(GenreSearchDto searchGenreDto, Pageable pageable);
}