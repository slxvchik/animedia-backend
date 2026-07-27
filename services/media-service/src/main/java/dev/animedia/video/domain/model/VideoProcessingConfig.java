package dev.animedia.video.domain.model;

import java.util.ArrayList;
import java.util.List;

public class VideoProcessingConfig {
	// paths to audios
	private final List<String> audioTracks = new ArrayList<>();
	// paths to subtitles
	private final List<String> subtitles = new ArrayList<>();
	private final List<Resolution> resolutions = new ArrayList<>();

	public void addAudio(String path) {
		this.audioTracks.add(path);
	}

	public void addSubtitle(String path) {

	}
}
