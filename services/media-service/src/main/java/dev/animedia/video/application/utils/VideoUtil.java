package dev.animedia.video.application.utils;

import dev.animedia.video.application.exception.VideoIOException;
import dev.animedia.video.domain.model.UploadFileCommand;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;

public final class VideoUtil {
	public static final List<String> VIDEO_TYPES = Arrays.asList("mp4", "mov");
	public static final List<String> AUDIO_TYPES = Arrays.asList("mp3", "wav");
	public static final List<String> SUBTITLE_TYPES = Arrays.asList("srt", "vtt", "ass", "ssa");

	public static final String VIDEO_DIR = "video";
	public static final String SUBTITLE_DIR = "subtitle";
	public static final String AUDIO_DIR = "audio";
	public static final String BUILD_DIR = "build";

	public static void clearAndCreateDirectories(String videoRootPath) throws IOException {
		clearDirectoryIfExists(videoRootPath, VIDEO_DIR);
		clearDirectoryIfExists(videoRootPath, SUBTITLE_DIR);
		clearDirectoryIfExists(videoRootPath, AUDIO_DIR);
		clearDirectoryIfExists(videoRootPath, BUILD_DIR);

		createDirectory(videoRootPath, VIDEO_DIR);
		createDirectory(videoRootPath, SUBTITLE_DIR);
		createDirectory(videoRootPath, AUDIO_DIR);
		createDirectory(videoRootPath, BUILD_DIR);
	}

	public static String saveFile(String basePath, String subdirectory, UploadFileCommand file) throws IOException {
		Path targetPath = Paths.get(
			basePath,
			subdirectory,
			generateFileName(file.filename(), file.contentType())
		);

		Files.copy(file.content(), targetPath, StandardCopyOption.REPLACE_EXISTING);
		return targetPath.toAbsolutePath().toString();
	}

	private static String generateFileName(String filename, String contentType) {
		return filename + "." + contentType;
	}

	private static void clearDirectoryIfExists(String basePath, String directoryName) throws IOException {
		Path directoryPath = Paths.get(basePath, directoryName);
		if (Files.exists(directoryPath) && Files.isDirectory(directoryPath)) {
			try (var paths = Files.walk(directoryPath)) {
				paths.sorted((a, b) -> b.compareTo(a))
					.forEach(VideoUtil::deletePathSafely);
			}
		}
	}

	private static void deletePathSafely(Path path) {
		try {
			Files.delete(path);
		} catch (IOException _) {
			throw new VideoIOException();
		}
	}

	private static void createDirectory(String basePath, String directoryName) throws IOException {
		Path directoryPath = Paths.get(basePath, directoryName);
		if (!Files.exists(directoryPath)) {
			Files.createDirectory(directoryPath);
		}
	}
}
