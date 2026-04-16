package dev.animedia.contentservice.application.genre.usecase;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import jakarta.annotation.Nullable;

public interface GetGenreUseCase {
	GenreDto get(Long id, @Nullable String languageCode);
}
