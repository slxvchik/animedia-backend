package dev.animedia.video.application.ports;

import dev.animedia.video.domain.model.VideoProcessingConfig;

import java.util.List;

public interface VideoEncoder {
	/**
	 * @return path to created video
	 */
	String encode(String outputDir, VideoProcessingConfig config);
}
