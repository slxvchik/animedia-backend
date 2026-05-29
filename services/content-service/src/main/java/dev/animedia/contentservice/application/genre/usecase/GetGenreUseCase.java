package dev.animedia.contentservice.application.genre.usecase;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import jakarta.annotation.Nullable;

import java.util.UUID;

public interface GetGenreUseCase {
	GenreDto get(UUID id, @Nullable Boolean active, @Nullable String languageCode);
}
