package dev.animedia.video.domain.model;

import java.io.InputStream;

public record UploadFileCommand(
	InputStream content,
	String filename,
	String contentType,
	long size
) {}
