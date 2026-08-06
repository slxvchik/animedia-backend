package dev.animedia.video.application.service;

import dev.animedia.shared.domain.exception.FieldRequiredException;
import dev.animedia.video.application.dto.SaveVideoDto;
import dev.animedia.video.application.exception.InvalidAudioTypeException;
import dev.animedia.video.application.exception.InvalidVideoTypeException;
import dev.animedia.video.application.exception.VideoIOException;
import dev.animedia.video.application.exception.VideoNotFoundException;
import dev.animedia.video.application.ports.VideoBuildEventPublisher;
import dev.animedia.video.application.usecase.SaveVideoUseCase;
import dev.animedia.video.application.utils.VideoUtil;
import dev.animedia.video.domain.model.UploadFileCommand;
import dev.animedia.video.domain.model.Video;
import dev.animedia.video.domain.model.VideoProcessingConfig;
import dev.animedia.video.domain.model.VideoStatus;
import dev.animedia.video.domain.repository.VideoCommandRepository;
import dev.animedia.video.domain.repository.VideoQueryRepository;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

public class SaveVideoService implements SaveVideoUseCase {
	private final VideoQueryRepository videoQueryRepository;
	private final VideoCommandRepository videoCommandRepository;
	private final VideoBuildEventPublisher videoBuildEventPublisher;
	private final String rootPath;

	public SaveVideoService(
		VideoQueryRepository videoQueryRepository,
		VideoCommandRepository videoCommandRepository,
		VideoBuildEventPublisher videoBuildEventPublisher,
		String rootPath
	) {
		this.videoQueryRepository = videoQueryRepository;
		this.videoCommandRepository = videoCommandRepository;
		this.videoBuildEventPublisher = videoBuildEventPublisher;
		this.rootPath = rootPath;
	}

	@Override
	public UUID execute(SaveVideoDto saveVideoDto) {
		validateInput(saveVideoDto);

		Video video = prepareVideo(saveVideoDto);
		UUID savedVideoId = videoCommandRepository.save(video);

		String videoRootPath = constructVideoPath(savedVideoId);

		processVideoFiles(videoRootPath, saveVideoDto);

		return savedVideoId;
	}

	private static void validateInput(SaveVideoDto saveVideoDto) {
		if (saveVideoDto.video() == null) {
			throw new FieldRequiredException("video");
		}

		if (!VideoUtil.VIDEO_TYPES.contains(saveVideoDto.video().contentType())) {
			throw new InvalidVideoTypeException(saveVideoDto.video().filename());
		}

		if (saveVideoDto.audioTracks() != null) {
			for (var audioTrack : saveVideoDto.audioTracks()) {
				if (!VideoUtil.AUDIO_TYPES.contains(audioTrack.contentType())) {
					throw new InvalidAudioTypeException(audioTrack.filename());
				}
			}
		}

		if (saveVideoDto.subtitles() != null) {
			for (var subtitle : saveVideoDto.subtitles()) {
				if (!VideoUtil.SUBTITLE_TYPES.contains(subtitle.contentType())) {
					throw new InvalidAudioTypeException(subtitle.filename());
				}
			}
		}
	}

	private Video prepareVideo(SaveVideoDto saveVideoDto) {
		if (saveVideoDto.id() != null) {
			var video = videoQueryRepository.findById(saveVideoDto.id())
				.orElseThrow(VideoNotFoundException::new);
			video.update(VideoStatus.PROCESSING);
			return video;
		} else {
			return new Video(UUID.randomUUID(), saveVideoDto.format(), VideoStatus.PROCESSING);
		}
	}

	private String constructVideoPath(UUID videoId) {
		return Paths.get(rootPath, videoId.toString()).toString();
	}

	private void processVideoFiles(String videoRootPath, SaveVideoDto saveVideoDto) {
		try {
			VideoUtil.clearAndCreateDirectories(videoRootPath);

			VideoProcessingConfig config = createVideoProcessingConfig(videoRootPath, saveVideoDto);

			videoBuildEventPublisher.publishVideoBuildProcess(videoRootPath, config);
		} catch (IOException _) {
			throw new VideoIOException();
		}
	}

	private VideoProcessingConfig createVideoProcessingConfig(String videoRootPath, SaveVideoDto saveVideoDto) throws IOException {
		VideoProcessingConfig config = new VideoProcessingConfig();

		// Process main video file
		config.setVideo(
			VideoUtil.saveFile(videoRootPath, VideoUtil.VIDEO_DIR, saveVideoDto.video())
		);

		// Process audio tracks
		if (saveVideoDto.audioTracks() != null) {
			for (UploadFileCommand audioTrack : saveVideoDto.audioTracks()) {
				if (audioTrack != null) {
					config.addAudio(
						VideoUtil.saveFile(videoRootPath, VideoUtil.AUDIO_DIR, audioTrack)
					);
				}
			}
		}

		// Process subtitles
		if (saveVideoDto.subtitles() != null) {
			for (UploadFileCommand subtitle : saveVideoDto.subtitles()) {
				if (subtitle != null) {
					config.addSubtitle(
						VideoUtil.saveFile(videoRootPath, VideoUtil.SUBTITLE_DIR, subtitle)
					);
				}
			}
		}

		return config;
	}
}
