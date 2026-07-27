package dev.animedia.image.domain.model;

import java.util.UUID;

public class Image {
	private final UUID id;
	private String path;
	private Integer width;
	private Integer height;
	private String resolution;
	private String alt;
	private String title;

	public Image(UUID id, String path, Integer width, Integer height, String resolution, String alt, String title) {
		this.id = id;
		this.path = path;
		this.width = width;
		this.height = height;
		this.resolution = resolution;
		this.alt = alt;
		this.title = title;
	}

	public UUID getId() {
		return id;
	}

	public String getPath() {
		return path;
	}

	public Integer getWidth() {
		return width;
	}

	public Integer getHeight() {
		return height;
	}

	public String getResolution() {
		return resolution;
	}

	public String getAlt() {
		return alt;
	}

	public String getTitle() {
		return title;
	}
}
