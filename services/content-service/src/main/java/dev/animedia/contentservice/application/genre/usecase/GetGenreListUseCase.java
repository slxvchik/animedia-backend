package dev.animedia.contentservice.application.genre.usecase;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

public interface GetGenreListUseCase {
	List<GenreDto> getList(List<UUID> idList, @Nullable Boolean active, @Nullable String languageCode);
}
