package dev.animedia.contentservice.series.domain.model;

import dev.animedia.contentservice.shared.domain.exception.FieldRequiredException;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Series {
	private final UUID id;
	private Integer episode;
	private String contentId;
	private Boolean active;
	private Integer duration;
	private LocalDate releaseDate;
	private String videoId;
	private final Set<SeriesTranslation> translations = new HashSet<>();

	public Series(
		UUID id,
		Integer episode,
		String contentId,
		Boolean active,
		Integer duration,
		LocalDate releaseDate,
		String videoId,
		Set<SeriesTranslation> translations
	) {
		this.id = id;
		setEpisode(episode);
		setContentId(contentId);
		setActive(active);
		setDuration(duration);
		this.releaseDate = releaseDate;
		setVideoId(videoId);
		setTranslations(translations);
	}

	public void update(
		Integer episode,
		String contentId,
		Boolean active,
		Integer duration,
		LocalDate releaseDate,
		String videoId,
		Set<SeriesTranslation> translations
	) {
		setContentId(contentId);
		setActive(active);
		setDuration(duration);
		setEpisode(episode);
		this.releaseDate = releaseDate;
		setVideoId(videoId);
		setTranslations(translations);
	}

	public UUID getId() {
		return id;
	}

	public String getContentId() {
		return contentId;
	}

	public boolean getActive() {
		return active;
	}

	public Integer getDuration() {
		return duration;
	}

	public int getEpisode() {
		return episode;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public String getVideoId() {
		return videoId;
	}

	public Set<SeriesTranslation> getTranslations() {
		return Collections.unmodifiableSet(translations);
	}

	private void setContentId(String contentId) {
		if (contentId == null || contentId.isBlank()) {
			throw new FieldRequiredException("contentId");
		}
		this.contentId = contentId;
	}

	private void setActive(Boolean active) {
		this.active = active != null && active;
	}

	private void setDuration(Integer duration) {
		this.duration = duration == null || duration <= 0 ? null : duration;
	}

	private void setEpisode(Integer episode) {
		this.episode = episode != null ? Math.max(0, episode) : 0;
	}

	private void setVideoId(String videoId) {
		if (videoId == null || videoId.isBlank()) {
			throw new FieldRequiredException("videoId");
		}
		this.videoId = videoId;
	}

	private void setTranslations(Set<SeriesTranslation> translations) {
		this.translations.clear();
		if (translations != null) {
			this.translations.addAll(translations);
		}
	}
}
