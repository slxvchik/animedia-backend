package dev.animedia.video.application.service;

import dev.animedia.video.application.exception.VideoNotFoundException;
import dev.animedia.video.application.usecase.DeleteVideoUseCase;
import dev.animedia.video.domain.repository.VideoCommandRepository;
import dev.animedia.video.domain.repository.VideoQueryRepository;

import java.util.UUID;

public class DeleteVideoService implements DeleteVideoUseCase {
	private final VideoQueryRepository videoQueryRepository;
	private final VideoCommandRepository videoCommandRepository;

	public DeleteVideoService(VideoQueryRepository videoQueryRepository, VideoCommandRepository videoCommandRepository) {
		this.videoQueryRepository = videoQueryRepository;
		this.videoCommandRepository = videoCommandRepository;
	}

	@Override
	public void execute(UUID id) {
		videoQueryRepository.findById(id)
			.orElseThrow(VideoNotFoundException::new);

	}
}
