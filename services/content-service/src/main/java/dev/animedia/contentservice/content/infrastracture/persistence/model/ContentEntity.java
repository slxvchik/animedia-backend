package dev.animedia.contentservice.content.infrastracture.persistence.model;

import dev.animedia.contentservice.content.domain.model.ContentType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/// TODO: create unique index in flywawy migration: ALTER TABLE content ADD CONSTRAINT uidx_content_alias_type_season UNIQUE NULLS NOT DISTINCT (alias, type, season);

@Entity
@Table(
    name = "content",
    indexes = {
        @Index(name = "idx_content_alias_type", columnList = "alias,content_type"),
        @Index(name = "idx_content_type", columnList = "content_type"),
        @Index(name = "idx_content_release_date", columnList = "release_date"),
    },
    check = @CheckConstraint(
        name = "content_season_positive",
        constraint = "season > 0"
    )
)
public class ContentEntity {
    @Id
    private UUID id;

    @PrePersist
    private void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @Column(name = "alias", nullable = false, updatable = false, length = 512)
    private String alias;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "content_type", nullable = false, updatable = false)
    private ContentType contentType;

    @Column(name = "season", nullable = false, updatable = false)
    private Integer season;

    @Column(name = "status_id", length = 512)
    private String statusId;

    @Column(name = "cover_image_id", length = 512)
    private String coverImageId;

    @Column(name = "trailer_video_id", length = 512)
    private String trailerVideoId;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "content_languages", joinColumns = @JoinColumn(name = "content_id"))
    @Column(name = "language_code")
    private Set<String> languageCodes = new HashSet<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "content_genres", joinColumns = @JoinColumn(name = "content_id"))
    @Column(name = "genre_id")
    private Set<String> genreIds = new HashSet<>();

    @OneToMany(
        mappedBy = "contentEntity",
        fetch = FetchType.LAZY,
        orphanRemoval = true
    )
    private Set<ContentTranslationEntity> translations = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public void setContentType(ContentType contentType) {
        this.contentType = contentType;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusEntity) {
        this.statusId = statusEntity;
    }

    public String getCoverImageId() {
        return coverImageId;
    }

    public void setCoverImageId(String coverImageId) {
        this.coverImageId = coverImageId;
    }

    public String getTrailerVideoId() {
        return trailerVideoId;
    }

    public void setTrailerVideoId(String trailerVideoId) {
        this.trailerVideoId = trailerVideoId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Set<String> getLanguageCodes() {
        return languageCodes;
    }

    public void setLanguageCodes(Set<String> languageCodeSet) {
        this.languageCodes = languageCodeSet;
    }

    public Set<String> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(Set<String> genreIdSet) {
        this.genreIds = genreIdSet;
    }

    public Set<ContentTranslationEntity> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<ContentTranslationEntity> translationSet) {
        this.translations = translationSet;
    }

    public void syncLanguageCodeSet(Set<String> newLanguageCodeSet) {
        if (newLanguageCodeSet == null || newLanguageCodeSet.isEmpty()) {
            this.languageCodes.clear();
            return;
        }
        this.languageCodes.retainAll(newLanguageCodeSet);
        this.languageCodes.addAll(newLanguageCodeSet);
    }

    public void syncGenreSet(Set<String> newGenreIdSet) {
        if (newGenreIdSet == null || newGenreIdSet.isEmpty()) {
            this.genreIds.clear();
            return;
        }

        this.genreIds.removeIf(existing -> !newGenreIdSet.contains(existing));

        for (String newGenreId : newGenreIdSet) {
            if (newGenreId != null) this.genreIds.add(newGenreId);
        }
    }

    public void syncTranslationSet(Set<ContentTranslationEntity> newContentTranslationEntitySet) {
        if (newContentTranslationEntitySet == null || newContentTranslationEntitySet.isEmpty()) {
            this.translations.clear();
            return;
        }

        this.translations.removeIf(existing -> !newContentTranslationEntitySet.contains(existing));

        for (ContentTranslationEntity newCte : newContentTranslationEntitySet) {
            this.translations.stream()
                .filter(existing -> existing.equals(newCte))
                .findFirst()
                .ifPresentOrElse(
                    existing -> {
                        existing.setTitle(newCte.getTitle());
                        existing.setDescription(newCte.getDescription());
                    },
                    () -> {
                        newCte.setId(null);
                        newCte.setContentEntity(this);
                        this.translations.add(newCte);
                    }
                );
        }
    }
}
