package dev.animedia.contentservice.movie.domain.model;

import dev.animedia.contentservice.shared.domain.exception.FieldRequiredException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Movie {
	private final String contentId;
	private String videoId;
	private Integer duration;
	private final Set<String> languageCodes = new HashSet<>();

	public Movie(String contentId) {
		if (contentId == null || contentId.isBlank()) {
			throw new FieldRequiredException("contentId");
		}
		this.contentId = contentId;
	}

	public void update(String videoId, Integer duration, Set<String> languageCodeSet) {
		this.videoId = videoId;
		this.duration = duration != null ? Math.max(0, duration) : null;
		this.languageCodes.clear();
		if (languageCodeSet != null) {
			this.languageCodes.addAll(languageCodeSet);
		}
	}

	public String getVideoId() {
		return videoId;
	}

	public String getContentId() {
		return contentId;
	}

	public Integer getDuration() {
		return duration;
	}

	public Set<String> getLanguageCodes() {
		return Collections.unmodifiableSet(languageCodes);
	}
}
