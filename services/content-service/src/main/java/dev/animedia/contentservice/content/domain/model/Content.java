package dev.animedia.contentservice.content.domain.model;

import dev.animedia.contentservice.content.domain.exception.ContentStatusRequiredException;
import dev.animedia.contentservice.content.domain.exception.ContentTypeRequiredException;
import dev.animedia.contentservice.shared.domain.slugalias.SlugAlias;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Content {
	private final UUID id;
	private final SlugAlias alias;
	private final ContentType type;
	private final int season;
	private String statusId;
	private String coverImageId;
	private String trailerVideoId;
	private LocalDate releaseDate;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private boolean active;
	private int sort;
	private Set<String> languageCodeSet = new HashSet<>();
	private Set<String> genreIdSet = new HashSet<>();
	private Set<ContentTranslation> translationSet = new HashSet<>();

	private static void validateType(ContentType type) {
		if (type == null) throw new ContentTypeRequiredException();
	}

	private static void validateStatus(String statusId) {
		if (statusId == null || statusId.isBlank()) throw new ContentStatusRequiredException();
	}

	private Content(Builder builder) {
		this.id = builder.id;
		this.alias = builder.alias;
		this.type = builder.type;
		this.season = Math.max(builder.season, 0);
		this.statusId = builder.statusId;
		this.coverImageId = builder.coverUrlId;
		this.trailerVideoId = builder.trailerUrlId;
		this.releaseDate = builder.releaseDate;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
		this.active = builder.active;
		setSort(builder.sort);
		this.languageCodeSet = builder.languageCodeSet;
		this.genreIdSet = builder.genreIdSet;
		this.translationSet = builder.translationSet;
	}

	public UUID getId() {
		return id;
	}

	public String getAlias() {
		return alias.getValue();
	}

	public ContentType getType() {
		return type;
	}

	public int getSeason() {
		return season;
	}

	public String getStatusId() {
		return statusId;
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

	public Set<String> getGenreIdSet() {
		return Collections.unmodifiableSet(genreIdSet);
	}

	public Set<ContentTranslation> getTranslationSet() {
		return Collections.unmodifiableSet(translationSet);
	}

	public void update(
		ContentUpdate contentUpdate
	) {
		this.statusId = contentUpdate.statusId();
		this.coverImageId = contentUpdate.coverImageId();
		this.trailerVideoId = contentUpdate.trailerVideoId();
		this.releaseDate = contentUpdate.releaseDate();
		this.updatedAt = LocalDateTime.now();
		this.active = contentUpdate.active();
		setSort(contentUpdate.sort());
		setLanguageCodeSet(contentUpdate.languageCodeSet());
		setGenreIdSet(contentUpdate.genreIdSet());
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

	private void setGenreIdSet(Set<String> genreIdSet) {
		if (genreIdSet != null) {
			this.genreIdSet.retainAll(genreIdSet);
			this.genreIdSet.addAll(genreIdSet);
		} else {
			this.genreIdSet.clear();
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
		private SlugAlias alias;
		private ContentType type;
		private int season;
		private String statusId;
		private String coverUrlId;
		private String trailerUrlId;
		private LocalDate releaseDate;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
		private boolean active;
		private int sort;
		private Set<String> languageCodeSet;
		private Set<String> genreIdSet;
		private Set<ContentTranslation> translationSet;

		public Content build() {
			Content.validateStatus(this.statusId);
			Content.validateType(this.type);

			if (this.createdAt == null) {
				this.createdAt = LocalDateTime.now();
			}

			return new Content(this);
		}

		public Builder id(UUID id) {
			this.id = id;
			return this;
		}

		public Builder alias(String alias) {
			this.alias = new SlugAlias(alias);
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

		public Builder statusId(String statusId) {
			this.statusId = statusId;
			return this;
		}

		public Builder coverUrlId(String coverUrlId) {
			this.coverUrlId = coverUrlId;
			return this;
		}

		public Builder trailerUrlId(String trailerUrlId) {
			this.trailerUrlId = trailerUrlId;
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

		public Builder genreIdSet(Set<String> genreIdSet) {
			this.genreIdSet = genreIdSet != null ? genreIdSet : Set.of();
			return this;
		}

		public Builder translationSet(Set<ContentTranslation> translationSet) {
			this.translationSet = translationSet != null ? translationSet : Set.of();
			return this;
		}
	}
}
