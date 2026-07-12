package dev.animedia.contentservice.genre.application.usecase;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

public interface GetGenreListUseCase {
	List<GenreDto> get(List<UUID> idList, @Nullable String languageCode);
}
