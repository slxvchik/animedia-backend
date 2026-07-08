package dev.animedia.contentservice.application.genre.usecase.admin;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;

public interface GetAllGenreUseCase {
	Page<GenreDto> get(Pageable pageable);
}