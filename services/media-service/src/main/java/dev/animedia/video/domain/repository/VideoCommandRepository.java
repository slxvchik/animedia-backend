package dev.animedia.video.domain.repository;

import dev.animedia.video.domain.model.Video;

import java.util.UUID;

public interface VideoCommandRepository {
	UUID save(Video video);
	void delete(UUID id);
}
