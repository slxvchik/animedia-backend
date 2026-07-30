package dev.animedia.video.domain.model;

import java.util.UUID;

public class Video {
	private final UUID id;
	private VideoFormat format;
	private VideoStatus status;
	/**
	 * TODO:
	 * relations dub studios which created audio track
	 */
	/*
	private final List<String> audioLanguageCodes = new ArrayList<>();
	private final List<String> dubStudioIds = new ArrayList<>();
	*/
	/**
	 * TODO:
	 * relations translation teams which created subtitles
	 */
	/*
	private final List<String> subtitleLanguageCodes = new ArrayList<>();
	private final List<String> subtitleTranslationTeamIds = new ArrayList<>();
	*/

	public Video(UUID id, VideoFormat format, VideoStatus status) {
		this.id = id;
		this.format = format;
		this.status = status;
	}

	public void update(VideoStatus status) {
		this.status = status;
	}

	/**
	 * example: /var/www/dev.animedia.com/services/media-service/storage/video/id
	 * @return path dir for sources generated from id
	 */
	public String getVideoPath() {
		return id.toString();
	}

	public UUID getId() {
		return id;
	}

	public VideoFormat getFormat() {
		return format;
	}

	public VideoStatus getStatus() {
		return status;
	}
}
