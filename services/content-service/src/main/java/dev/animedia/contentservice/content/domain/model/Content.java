package dev.animedia.contentservice.content.domain.model;

import dev.animedia.contentservice.content.domain.exception.ContentStatusRequiredException;
import dev.animedia.contentservice.content.domain.exception.ContentTypeRequiredException;
import dev.animedia.contentservice.shared.domain.exception.FieldRequiredException;
import dev.animedia.contentservice.shared.domain.slugalias.SlugAlias;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class Content {
	private final UUID id;
	private final SlugAlias alias;
	private final ContentType type;
	private final Integer season;
	private String statusId;
	private String coverImageId;
	private String trailerVideoId;
	private LocalDate releaseDate;
	private final LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private Boolean active;
	private Integer sort;
	private Set<String> languageCodes = new HashSet<>();
	private Set<String> genreIds = new HashSet<>();
	private Set<ContentTranslation> translations = new HashSet<>();

	private static void validateType(ContentType type) {
		if (type == null) throw new ContentTypeRequiredException();
	}

	private static void validateStatus(String statusId) {
		if (statusId == null || statusId.isBlank()) throw new ContentStatusRequiredException();
	}

	private Content(Builder builder) {
		validateStatus(builder.statusId);
		this.id = builder.id;
		this.alias = builder.alias;
		this.type = builder.type;
		this.season = builder.season != null ? Math.max(0, builder.season) : 0;
		setStatusId(builder.statusId);
		this.coverImageId = builder.coverUrlId;
		this.trailerVideoId = builder.trailerUrlId;
		this.releaseDate = builder.releaseDate;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
		setActive(builder.active);
		setSort(builder.sort);
		setLanguageCodes(builder.languageCodes);
		setGenreIds(builder.genreIds);
		setTranslations(builder.translationSet);
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

	public Integer getSeason() {
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

	public Boolean getActive() {
		return active;
	}

	public Integer getSort() {
		return sort;
	}

	public Set<String> getLanguageCodes() {
		return Collections.unmodifiableSet(languageCodes);
	}

	public Set<String> getGenreIds() {
		return Collections.unmodifiableSet(genreIds);
	}

	public Set<ContentTranslation> getTranslations() {
		return Collections.unmodifiableSet(translations);
	}

	public void update(
		UpdateContent updateContent
	) {
		setStatusId(updateContent.statusId());
		this.coverImageId = updateContent.coverImageId();
		this.trailerVideoId = updateContent.trailerVideoId();
		this.releaseDate = updateContent.releaseDate();
		this.updatedAt = LocalDateTime.now();
		setActive(updateContent.active());
		setSort(updateContent.sort());
		setLanguageCodes(updateContent.languageCodes());
		setGenreIds(updateContent.genreIds());
		setTranslations(updateContent.translations());
	}

	private void setStatusId(String statusId) {
		if (statusId == null || statusId.isBlank()) {
			throw new FieldRequiredException("statusId");
		}
		this.statusId = statusId;
	}

	private void setActive(Boolean active) {
		this.active = active != null && active;
	}

	private void setSort(Integer sort) {
		this.sort = sort != null ? Math.max(sort, 0) : 0;
	}

	private void setLanguageCodes(Set<String> languageCodes) {
		this.languageCodes.clear();
		if (languageCodes != null) {
			this.languageCodes.addAll(languageCodes);
		}
	}

	private void setGenreIds(Set<String> genreIds) {
		this.genreIds.clear();
		if (genreIds != null) {
			this.genreIds.addAll(genreIds);
		}
	}

	private void setTranslations(Set<ContentTranslation> translations) {
		this.translations.clear();
		this.translations.addAll(translations);
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Content content)) { return false; }
        return Objects.equals(season, content.season) &&
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
		private Integer season;
		private String statusId;
		private String coverUrlId;
		private String trailerUrlId;
		private LocalDate releaseDate;
		private LocalDateTime createdAt;
		private LocalDateTime updatedAt;
		private Boolean active;
		private Integer sort;
		private Set<String> languageCodes;
		private Set<String> genreIds;
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

		public Builder season(Integer season) {
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

		public Builder active(Boolean active) {
			this.active = active;
			return this;
		}

		public Builder sort(Integer sort) {
			this.sort = sort;
			return this;
		}

		public Builder languageCodes(Set<String> languageCodeSet) {
			this.languageCodes = languageCodeSet != null ? languageCodeSet : Set.of();
			return this;
		}

		public Builder genreIds(Set<String> genreIdSet) {
			this.genreIds = genreIdSet != null ? genreIdSet : Set.of();
			return this;
		}

		public Builder translation(Set<ContentTranslation> translationSet) {
			this.translationSet = translationSet != null ? translationSet : Set.of();
			return this;
		}
	}
}
