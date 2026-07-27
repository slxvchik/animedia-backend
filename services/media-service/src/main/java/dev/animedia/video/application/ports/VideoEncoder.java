package dev.animedia.video.application.ports;

import dev.animedia.video.domain.model.VideoProcessingConfig;

public interface VideoEncoder {
	/**
	 * @return path to created video
	 */
	String encode(String inputPathToFile, String outputDir, VideoProcessingConfig config);
}
