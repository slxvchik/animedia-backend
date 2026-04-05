package dev.animedia.contentservice.old.content.model;

import dev.animedia.contentservice.old.status.model.ContentStatus;
import dev.animedia.contentservice.old.genre.model.Genre;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/// TODO: create unique index in flywawy migration: ALTER TABLE content ADD CONSTRAINT uidx_content_alias_type_season UNIQUE NULLS NOT DISTINCT (alias, type, season);

@Entity
@Table(
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
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @Column(nullable = false, length = 512)
    private String alias;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ContentType type;

    @Column
    private Integer season;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    private ContentStatus status;

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

    @Column(nullable = false)
    private Boolean active = false;

    @Column(nullable = false)
    private Integer sort;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "content_languages", joinColumns = @JoinColumn(name = "content_uuid"))
    @Column(name = "language_code")
    private Set<String> languageCodes = new HashSet<>();

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
    private Set<Genre> genres = new HashSet<>();

    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    private Set<ContentTranslation> translations = new HashSet<>();

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public ContentType getType() {
        return type;
    }

    public void setType(ContentType type) {
        this.type = type;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public ContentStatus getStatus() {
        return status;
    }

    public void setStatus(ContentStatus status) {
        this.status = status;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Set<String> getLanguageCodes() {
        return languageCodes;
    }

    public void setLanguageCodes(Set<String> languageCodes) {
        this.languageCodes = languageCodes;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Set<ContentTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<ContentTranslation> translations) {
        this.translations = translations;
    }
}
