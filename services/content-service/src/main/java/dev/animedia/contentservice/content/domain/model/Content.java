package dev.animedia.contentservice.content.domain.model;

import dev.animedia.contentservice.content.domain.exception.ContentInvalidAliasException;
import dev.animedia.contentservice.content.domain.exception.ContentStatusRequiredException;
import dev.animedia.contentservice.content.domain.exception.ContentTypeRequiredException;
import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.status.domain.model.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;

public class Content {
	private final UUID id;
	private final String alias;
	private final ContentType type;
	private final int season;
	private Status status;
	private String coverImageId;
	private String trailerVideoId;
	private LocalDate releaseDate;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean active;
	private int sort;
	private Set<String> languageCodeSet = new HashSet<>();
	private Set<Genre> genreSet = new HashSet<>();
	private Set<ContentTranslation> translationSet = new HashSet<>();

	private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

	private static void validateAlias(String alias) {
		if (!ALIAS_PATTERN.matcher(alias).matches()) throw new ContentInvalidAliasException();
	}

	private static void validateType(ContentType type) {
		if (type == null) throw new ContentTypeRequiredException();
	}

	private static void validateStatus(Status status) {
		if (status == null) throw new ContentStatusRequiredException();
	}

	private Content(Builder builder) {
		this.id = builder.id;
		this.alias = builder.alias;
		this.type = builder.type;
		this.season = Math.max(builder.season, 0);

		this.status = builder.status;
		this.coverImageId = builder.coverUrl;
		this.trailerVideoId = builder.trailerUrl;
		this.releaseDate = builder.releaseDate;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
		this.active = builder.active;
		setSort(builder.sort);
		this.languageCodeSet = builder.languageCodeSet;
		this.genreSet = builder.genreSet;
		this.translationSet = builder.translationSet;
	}

	public UUID getId() {
		return id;
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

	public String getCoverImageId() {
		return coverImageId;
	}

	public String getTrailerVideoId() {
		return trailerVideoId;
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

	public boolean getActive() {
		return active;
	}

	public int getSort() {
		return sort;
	}

	public Set<String> getLanguageCodeSet() {
		return Collections.unmodifiableSet(languageCodeSet);
	}

	public Set<Genre> getGenreSet() {
		return Collections.unmodifiableSet(genreSet);
	}

	public Set<ContentTranslation> getTranslationSet() {
		return Collections.unmodifiableSet(translationSet);
	}

	public void update(
		ContentUpdate contentUpdate
	) {
		this.status = contentUpdate.status();
		this.coverImageId = contentUpdate.coverImageId();
		this.trailerVideoId = contentUpdate.trailerVideoId();
		this.releaseDate = contentUpdate.releaseDate();
		this.updatedAt = LocalDateTime.now();
		this.active = contentUpdate.active();
		setSort(contentUpdate.sort());
		setLanguageCodeSet(contentUpdate.languageCodeSet());
		setGenreSet(contentUpdate.genreSet());
		setTranslationSet(contentUpdate.translationSet());
	}

	private void setSort(int sort) {
		this.sort = Math.max(sort, 0);
	}

	private void setLanguageCodeSet(Set<String> languageCodeSet) {
		if (languageCodeSet != null) {
			this.languageCodeSet.retainAll(languageCodeSet);
			this.languageCodeSet.addAll(languageCodeSet);
		} else {
			this.languageCodeSet.clear();
		}
	}

	private void setGenreSet(Set<Genre> genreSet) {
		if (genreSet != null) {
			this.genreSet.retainAll(genreSet);
			this.genreSet.addAll(genreSet);
		} else {
			this.genreSet.clear();
		}
	}

	private void setTranslationSet(Set<ContentTranslation> translationSet) {
		this.translationSet.clear();
		this.translationSet.addAll(translationSet);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Content content)) { return false; }
        return season == content.season &&
	        alias.equals(content.alias) &&
	        type.equals(content.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(alias, type, season);
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder {
		private UUID id;
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

		public Builder id(UUID id) {
			this.id = id;
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
