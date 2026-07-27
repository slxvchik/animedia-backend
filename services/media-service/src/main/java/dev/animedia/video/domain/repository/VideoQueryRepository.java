package dev.animedia.video.domain.repository;

import dev.animedia.video.domain.model.Video;

import java.util.Optional;
import java.util.UUID;

public interface VideoQueryRepository {
	Optional<Video> findById(UUID id);
	boolean existsByDirPath(String dirPath);
}
