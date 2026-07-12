package dev.animedia.contentservice.genre.application.usecase;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;

public interface IndexAllGenreUseCase {
	Page<GenreDto> index(Pageable pageable);
}