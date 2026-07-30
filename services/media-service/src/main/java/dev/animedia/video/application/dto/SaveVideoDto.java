package dev.animedia.video.application.dto;

import dev.animedia.video.domain.model.UploadFileCommand;
import dev.animedia.video.domain.model.VideoFormat;
import dev.animedia.video.domain.model.VideoProcessingConfig;
import dev.animedia.video.domain.model.VideoStatus;
import jakarta.annotation.Nullable;

import java.util.List;
import java.util.UUID;

/**
 * @param video in HIGHEST resolution
 */
public record SaveVideoDto(
	@Nullable
	UUID id,
	UploadFileCommand video,
	List<UploadFileCommand> audioTracks,
	List<UploadFileCommand> subtitles,
	VideoFormat format,
	VideoProcessingConfig videoProcessingConfig
) {}
