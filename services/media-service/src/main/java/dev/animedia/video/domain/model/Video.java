package dev.animedia.video.domain.model;

import dev.animedia.shared.domain.exception.FieldRequiredException;
import dev.animedia.video.domain.exception.VideoRootPathDirectoryDoesNotExistException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

public class Video {
	private final UUID id;
	private final String contentId;
	private String dirPath;
	private final List<String> languageCodes = new ArrayList<>();
	private String extension;
	private VideoFormat format;
	private VideoStatus status;

	private static final String ROOT_PATH;

	static {
		Properties properties = new Properties();
		String rootPath = "";
		try (InputStream input = Video.class.getClassLoader().getResourceAsStream("application.properties")) {
			if (input != null) {
				properties.load(input);
				rootPath = properties.getProperty("application.video.root-path");
				if (rootPath == null || rootPath.isBlank()) {
					throw new IllegalStateException("Property 'application.video.root-path' is missing or empty.");
				}
				Path path = Paths.get(rootPath);
				if (!Files.isDirectory(path)) {
					throw new IllegalStateException("Video RootPath doesn't exists.");
				}
			} else {
				throw new IllegalStateException("Video RootPath value not found. Please use basic folder /storage/video or create your own and change application.properties.");
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed to open application.properties: " + e.getMessage());
		}

		ROOT_PATH = rootPath;
	}

	public Video(UUID id, String contentId, String dirPath, List<String> languageCodes, String extension, VideoFormat format) {
		this.id = id;
		this.contentId = contentId;
		this.dirPath = dirPath;
		this.languageCodes.clear();
		this.languageCodes.addAll(languageCodes);
		this.extension = extension;
		this.format = format;
	}

	public void update(String filePath, List<String> languageCodes, String extension, VideoFormat format, VideoStatus status) {
	}

	public UUID getId() {
		return id;
	}

	public String getContentId() {
		return contentId;
	}

	public String getDirPath() {
		return dirPath;
	}

	public List<String> getLanguageCodes() {
		return languageCodes;
	}

	public String getExtension() {
		return extension;
	}

	public VideoFormat getFormat() {
		return format;
	}

	public static String getRootPath() {
		return ROOT_PATH;
	}

	private void setDirPath(String dirPath) {
		if (dirPath == null || dirPath.isBlank()) {
			throw new FieldRequiredException("FilePath");
		}
		Path path = Paths.get(ROOT_PATH);
		if (!Files.isDirectory(path)) {
			throw new VideoRootPathDirectoryDoesNotExistException();
		}
		this.dirPath = dirPath;
	}
}
