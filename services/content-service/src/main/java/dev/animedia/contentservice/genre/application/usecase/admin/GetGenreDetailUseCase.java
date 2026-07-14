package dev.animedia.contentservice.genre.application.usecase.admin;

import dev.animedia.contentservice.genre.application.dto.response.GenreDto;
import jakarta.annotation.Nullable;

import java.util.UUID;

public interface GetGenreDetailUseCase {
	GenreDto get(UUID id, @Nullable String languageCode);
}
