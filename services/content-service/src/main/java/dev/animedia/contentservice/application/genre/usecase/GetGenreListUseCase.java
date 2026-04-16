package dev.animedia.contentservice.application.genre.usecase;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import jakarta.annotation.Nullable;

import java.util.List;

public interface GetGenreListUseCase {
	List<GenreDto> getList(List<Long> idList, @Nullable String languageCode);
}
