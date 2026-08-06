package dev.animedia.video.application.ports;

import dev.animedia.video.domain.model.VideoProcessingConfig;

public interface VideoBuildEventPublisher {
	/**
	 * @return path to created video
	 */
	String publishVideoBuildProcess(String outputDir, VideoProcessingConfig config);
}
