package dev.animedia.contentservice.infrastructure.persistence.content.model;

import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusEntity;
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
        @Index(name = "idx_content_alias_type", columnList = "alias,type"),
        @Index(name = "idx_content_type", columnList = "type"),
        @Index(name = "idx_content_release_date", columnList = "release_date"),
    },
    check = @CheckConstraint(
        name = "content_season_positive",
        constraint = "season > 0"
    )
)
public class ContentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "alias", nullable = false, updatable = false, length = 512)
    private String alias;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "content_type", nullable = false, updatable = false)
    private ContentType contentType;

    @Column(name = "season", nullable = false, updatable = false)
    private Integer season;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusEntity statusEntity;

    @Column(name = "cover_url", length = 512)
    private String coverUrl;

    @Column(name = "trailer_url", length = 512)
    private String trailerUrl;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "content_languages", joinColumns = @JoinColumn(name = "content_uuid"))
    @Column(name = "language_code")
    private Set<String> languageCodeSet = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "content_genres",
        joinColumns = @JoinColumn(name = "content_uuid"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"),
        uniqueConstraints = {
            @UniqueConstraint(name = "uidx_content_genres_content_uuid_genre_id", columnNames = {"content_uuid", "genre_id"})
        },
        indexes = {
            @Index(name = "idx_content_languages_content_uuid_genre_id", columnList = "content_uuid,genre_id"),
            @Index(name = "idx_content_languages_genre_id", columnList = "genre_id")
        }
    )
    private Set<GenreEntity> genreSet = new HashSet<>();

    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<ContentTranslationEntity> translationSet = new HashSet<>();

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

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
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

    public Set<String> getLanguageCodeSet() {
        return languageCodeSet;
    }

    public void setLanguageCodeSet(Set<String> languageCodeSet) {
        this.languageCodeSet = languageCodeSet;
    }

    public Set<GenreEntity> getGenreSet() {
        return genreSet;
    }

    public void setGenreSet(Set<GenreEntity> genreSet) {
        this.genreSet = genreSet;
    }

    public Set<ContentTranslationEntity> getTranslationSet() {
        return translationSet;
    }

    public void setTranslationSet(Set<ContentTranslationEntity> translationSet) {
        this.translationSet = translationSet;
    }

    public void syncLanguageCodeSet(Set<String> newLanguageCodeSet) {
        if (newLanguageCodeSet == null) {
            this.languageCodeSet.clear();
            return;
        }
        this.languageCodeSet.retainAll(newLanguageCodeSet);
        this.languageCodeSet.addAll(newLanguageCodeSet);
    }

    public void syncGenreSet(Set<GenreEntity> newGenreSet) {
        if (newGenreSet == null) {
            this.genreSet.clear();
            return;
        }
        this.genreSet.retainAll(newGenreSet);
        for (GenreEntity newGe : newGenreSet) {
            if (newGe.getId() != null) this.genreSet.add(newGe);
        }
    }

    public void syncTranslationSet(Set<ContentTranslationEntity> newContentTranslationEntitySet) {
        if (newContentTranslationEntitySet == null) {
            this.translationSet.clear();
            return;
        }
        this.translationSet.retainAll(newContentTranslationEntitySet);
        for (ContentTranslationEntity newCte : newContentTranslationEntitySet) {
            if (newCte.getId() == null) {
                this.translationSet.add(newCte);
            } else {
                this.translationSet.stream()
                    .filter(cte -> cte.getId().equals(newCte.getId()))
                    .findFirst()
                    .ifPresent(cte -> {
                        cte.setTitle(newCte.getTitle());
                        cte.setDescription(newCte.getDescription());
                    });
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ContentEntity that)) return false;
        return alias.equals(that.alias) && contentType == that.contentType && season.equals(that.season);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias, contentType, season);
    }
}
