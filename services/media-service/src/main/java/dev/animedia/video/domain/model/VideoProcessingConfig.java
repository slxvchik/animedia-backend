package dev.animedia.video.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideoProcessingConfig {
	private String video;
	// paths to audios
	private final List<String> audioTracks = new ArrayList<>();
	// paths to subtitles
	private final List<String> subtitles = new ArrayList<>();
	private final List<Resolution> resolutions = new ArrayList<>();

	public void setVideo(String path) {
		this.video = path;
	}

	public void addAudio(String path) {
		this.audioTracks.add(path);
	}

	public void addSubtitle(String path) {
		this.subtitles.add(path);
	}

	public void addResolution(Resolution resolution) {
		this.resolutions.add(resolution);
	}

	public List<String> getAudioTracks() {
		return Collections.unmodifiableList(audioTracks);
	}

	public List<String> getSubtitles() {
		return Collections.unmodifiableList(subtitles);
	}

	public List<Resolution> getResolutions() {
		return Collections.unmodifiableList(resolutions);
	}
}
