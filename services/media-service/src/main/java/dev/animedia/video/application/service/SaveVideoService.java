package dev.animedia.video.application.service;

import dev.animedia.shared.domain.exception.FieldRequiredException;
import dev.animedia.video.application.dto.SaveVideoDto;
import dev.animedia.video.application.exception.InvalidAudioTypeException;
import dev.animedia.video.application.exception.InvalidVideoTypeException;
import dev.animedia.video.application.exception.VideoNotFoundException;
import dev.animedia.video.application.ports.VideoEncoder;
import dev.animedia.video.application.usecase.SaveVideoUseCase;
import dev.animedia.video.domain.model.Video;
import dev.animedia.video.domain.model.VideoProcessingConfig;
import dev.animedia.video.domain.model.VideoStatus;
import dev.animedia.video.domain.repository.VideoCommandRepository;
import dev.animedia.video.domain.repository.VideoQueryRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public class SaveVideoService implements SaveVideoUseCase {
	private final static String[] VIDEO_TYPES = {"mp4", "mov"};
	private final static String[] AUDIO_TYPES = {"mp3", "wav"};
	private final static String[] SUBTITLE_TYPES = {"srt", "vtt", "ass", "ssa"};

	private final VideoQueryRepository videoQueryRepository;
	private final VideoCommandRepository videoCommandRepository;
	private final VideoEncoder videoEncoder;
	private final String rootPath;

	public SaveVideoService(
		VideoQueryRepository videoQueryRepository,
		VideoCommandRepository videoCommandRepository,
		VideoEncoder videoEncoder,
		String rootPath
	) {
		this.videoQueryRepository = videoQueryRepository;
		this.videoCommandRepository = videoCommandRepository;
		this.videoEncoder = videoEncoder;
		this.rootPath = rootPath;
	}

	@Override
	public UUID execute(SaveVideoDto saveVideoDto) {
		if (saveVideoDto.video() == null) {
			throw new FieldRequiredException("video");
		}
		if (!Arrays.asList(VIDEO_TYPES).contains(saveVideoDto.video().contentType())) {
			throw new InvalidVideoTypeException(saveVideoDto.video().filename());
		}
		if (saveVideoDto.audioTracks() != null) {
			for (var audioTrack : saveVideoDto.audioTracks()) {
				if (!Arrays.asList(AUDIO_TYPES).contains(audioTrack.contentType())) {
					throw new InvalidAudioTypeException(audioTrack.filename());
				}
			}
		}
		if (saveVideoDto.subtitles() != null) {
			for (var subtitle : saveVideoDto.subtitles()) {
				if (!Arrays.asList(SUBTITLE_TYPES).contains(subtitle.contentType())) {
					throw new InvalidAudioTypeException(subtitle.filename());
				}
			}
		}

		Video video;
		if (saveVideoDto.id() != null) {
			video = videoQueryRepository.findById(saveVideoDto.id())
				.orElseThrow(VideoNotFoundException::new);
			video.update(VideoStatus.PROCESSING);
		} else {
			video = new Video(UUID.randomUUID(), saveVideoDto.format(), VideoStatus.PROCESSING);
		}

		UUID savedVideoId = videoCommandRepository.save(video);

		// Create source files
		try {
			String sources = rootPath + "/" + savedVideoId.toString() + "/sources";
			Files.createDirectory(Paths.get(sources));
			saveVideoDto.video()
			Files.createDirectory(Paths.get(sources + "/audio"));
			Files.createDirectory(Paths.get(sources + "/subtitles"));
			Files.createDirectory(Paths.get(sources + "/build"));

			VideoProcessingConfig config = new VideoProcessingConfig();
			config.setVideo(saveVideoDto.);
			videoEncoder.encode(Paths.get(sources + "/build").toAbsolutePath(), )
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
}
