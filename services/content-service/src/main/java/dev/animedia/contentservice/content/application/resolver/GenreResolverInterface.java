package dev.animedia.contentservice.content.application.resolver;

import dev.animedia.contentservice.content.application.dto.genre.GenreDto;

import java.util.List;
import java.util.Set;

public interface GenreResolverInterface {
	List<GenreDto> resolve(Set<String> requestedGenreIds);
}
