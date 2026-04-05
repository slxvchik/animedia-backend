package dev.animedia.contentservice.domain.content.model;

import dev.animedia.contentservice.domain.content.exception.ContentInvalidAliasException;
import dev.animedia.contentservice.domain.content.exception.ContentStatusRequiredException;
import dev.animedia.contentservice.domain.content.exception.ContentTypeRequiredException;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.status.model.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

public class Content {
	private final UUID uuid;
	private String alias;
	private ContentType type;
	private int season;
	private Status status;
	private String coverUrl;
	private String trailerUrl;
	private LocalDate releaseDate;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean active;
	private int sort;
	private Set<String> languageCodeSet;
	private Set<Genre> genreSet;
	private Set<ContentTranslation> translationSet;

	private final static Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

	private static void validateAlias(String alias) {
		if (!ALIAS_PATTERN.matcher(alias).hasMatch()) throw new ContentInvalidAliasException();
	}

	private static void validateType(ContentType type) {
		if (type == null) throw new ContentTypeRequiredException();
	}

	private static void validateStatus(Status status) {
		if (status == null) throw new ContentStatusRequiredException();
	}

	private Content(Builder builder) {
		this.uuid = builder.uuid;
		this.alias = builder.alias;
		this.type = builder.type;
		setSeason(builder.season);
		this.status = builder.status;
		this.coverUrl = builder.coverUrl;
		this.trailerUrl = builder.trailerUrl;
		this.releaseDate = builder.releaseDate;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
		this.active = builder.active;
		setSort(builder.sort);
		this.languageCodeSet = builder.languageCodeSet;
		this.genreSet = builder.genreSet;
		this.translationSet = builder.translationSet;
	}

	public static Builder builder() {
		return new Builder();
	}

	public void update(
		String alias,
		ContentType type,
		int season,
		Status status,
		String coverUrl,
		String trailerUrl,
		LocalDate releaseDate,
		boolean active,
		int sort,
		Set<String> languageCodeSet,
		Set<Genre> genreSet,
		Set<ContentTranslation> translationSet
	) {
		validateAlias(alias);
		validateType(type);
		validateStatus(status);

		this.alias = alias;
		this.type = type;
		setSeason(season);
		this.status = status;
		this.coverUrl = coverUrl;
		this.trailerUrl = trailerUrl;
		this.releaseDate = releaseDate;
		this.updatedAt = LocalDateTime.now();
		this.active = active;
		setSort(sort);
		this.languageCodeSet = languageCodeSet != null ? languageCodeSet : Set.of();
		this.genreSet = genreSet != null ? genreSet : Set.of();
		this.translationSet = translationSet != null ? translationSet : Set.of();
	}

	private void setSeason(int season) {
		this.season = Math.max(season, 0);
	}

	private void setSort(int sort) {
		this.sort = Math.max(sort, 0);
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getAlias() {
		return alias;
	}

	public ContentType getType() {
		return type;
	}

	public int getSeason() {
		return season;
	}

	public Status getStatus() {
		return status;
	}

	public String getCoverUrl() {
		return coverUrl;
	}

	public String getTrailerUrl() {
		return trailerUrl;
	}

	public LocalDate getReleaseDate() {
		return releaseDate;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public boolean isActive() {
		return active;
	}

	public int getSort() {
		return sort;
	}

	public Set<String> getLanguageCodeSet() {
		return languageCodeSet;
	}

	public Set<Genre> getGenreSet() {
		return genreSet;
	}

	public Set<ContentTranslation> getTranslationSet() {
		return translationSet;
	}

	public static class Builder {
		private UUID uuid;
		private String alias;
		private ContentType type;
		private int season;
		private Status status;
		private String coverUrl;
		private String trailerUrl;
		private LocalDate releaseDate;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
		private boolean active;
		private int sort;
		private Set<String> languageCodeSet;
		private Set<Genre> genreSet;
		private Set<ContentTranslation> translationSet;

		public Content build() {
			Content.validateAlias(this.alias);
			Content.validateStatus(this.status);
			Content.validateType(this.type);

			if (this.createdAt == null) {
				this.createdAt = LocalDateTime.now();
			}
			if (this.updatedAt == null) {
				this.updatedAt = LocalDateTime.now();
			}

			return new Content(this);
		}

		public Builder uuid(UUID uuid) {
			this.uuid = uuid;
			return this;
		}

		public Builder alias(String alias) {
			this.alias = alias;
			return this;
		}

		public Builder type(ContentType type) {
			this.type = type;
			return this;
		}

		public Builder season(int season) {
			this.season = season;
			return this;
		}

		public Builder status(Status status) {
			this.status = status;
			return this;
		}

		public Builder coverUrl(String coverUrl) {
			this.coverUrl = coverUrl;
			return this;
		}

		public Builder trailerUrl(String trailerUrl) {
			this.trailerUrl = trailerUrl;
			return this;
		}

		public Builder releaseDate(LocalDate releaseDate) {
			this.releaseDate = releaseDate;
			return this;
		}

		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}

		public Builder updatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}

		public Builder active(boolean active) {
			this.active = active;
			return this;
		}

		public Builder sort(int sort) {
			this.sort = sort;
			return this;
		}

		public Builder languageCodeSet(Set<String> languageCodeSet) {
			this.languageCodeSet = languageCodeSet != null ? languageCodeSet : Set.of();
			return this;
		}

		public Builder genreSet(Set<Genre> genreSet) {
			this.genreSet = genreSet != null ? genreSet : Set.of();
			return this;
		}

		public Builder translationSet(Set<ContentTranslation> translationSet) {
			this.translationSet = translationSet != null ? translationSet : Set.of();
			return this;
		}
	}
}
