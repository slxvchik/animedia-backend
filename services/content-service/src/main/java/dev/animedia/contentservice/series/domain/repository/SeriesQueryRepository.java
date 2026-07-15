package dev.animedia.contentservice.series.domain.repository;

import dev.animedia.contentservice.series.domain.model.Series;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SeriesQueryRepository {
	Optional<Series> findById(UUID id);
	List<Series> findByContentId(List<String> contentId);
	boolean existsByEpisodeAndContentId(Integer episode, String contentId);
	boolean existsByEpisodeAndContentIdExcludeById(Integer episode, String contentId, UUID id);
}
