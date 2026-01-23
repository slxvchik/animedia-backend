package dev.animedia.contentservice.content.core;

import dev.animedia.contentservice.bookchapter.core.BookChapter;
import dev.animedia.contentservice.comicchapter.core.ComicChapter;
import dev.animedia.contentservice.contentstatus.core.ContentStatus;
import dev.animedia.contentservice.genre.core.Genre;
import dev.animedia.contentservice.language.Language;
import dev.animedia.contentservice.movie.Movie;
import dev.animedia.contentservice.series.core.Series;
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
    private Boolean active = true;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "content_languages",
        joinColumns = @JoinColumn(name = "content_uuid"),
        inverseJoinColumns = @JoinColumn(name = "language_code"),
        uniqueConstraints = {
            @UniqueConstraint(name = "uidx_content_languages_content_uuid_genre_id", columnNames = {"content_uuid", "language_code"})
        },
        indexes = {
            @Index(name = "idx_content_languages_content_uuid_language_code", columnList = "content_uuid,language_code"),
            @Index(name = "idx_content_languages_language_code", columnList = "language_code")
        }
    )
    private Set<Language> languages = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
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

    @OneToOne(mappedBy = "content")
    private Movie movie;

    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    private Set<Series> series = new HashSet<>();

    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    private Set<ComicChapter> comicChapters = new HashSet<>();

    @OneToMany(mappedBy = "content", fetch = FetchType.LAZY)
    private Set<BookChapter> books = new HashSet<>();

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

    public Set<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(Set<Language> languages) {
        this.languages = languages;
    }

    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Set<Series> getSeries() {
        return series;
    }

    public void setSeries(Set<Series> series) {
        this.series = series;
    }

    public Set<ComicChapter> getComicChapters() {
        return comicChapters;
    }

    public void setComicChapters(Set<ComicChapter> comicChapters) {
        this.comicChapters = comicChapters;
    }

    public Set<BookChapter> getBooks() {
        return books;
    }

    public void setBooks(Set<BookChapter> books) {
        this.books = books;
    }
}
