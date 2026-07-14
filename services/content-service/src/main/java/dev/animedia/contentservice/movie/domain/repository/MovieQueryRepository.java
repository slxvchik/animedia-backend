package dev.animedia.contentservice.movie.domain.repository;

import dev.animedia.contentservice.movie.domain.model.Movie;

import java.util.Optional;

public interface MovieQueryRepository {
	Optional<Movie> findByContentId(String contentId);
	boolean existsByContentId(String contentId);
}
