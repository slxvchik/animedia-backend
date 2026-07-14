package dev.animedia.contentservice.movie.domain.repository;

import dev.animedia.contentservice.movie.domain.model.Movie;

import java.util.UUID;

public interface MovieCommandRepository {
	UUID create(Movie movie);
	void update(Movie movie);
	void delete(UUID id);
}
