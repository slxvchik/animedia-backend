package dev.animedia.contentservice.genre.model;

import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;

@Entity
@Table(
    indexes = {
        @Index(name = "idx_genre_translation_genre_id_language_code", columnList = "genre_id,language_code"),
        @Index(name = "idx_genre_translation_language_code", columnList = "language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "uidx_genre_translation_genre_id_language_code", columnNames = {"genre_id", "language_code"})
    }
)
public class GenreTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_code", nullable = false)
    private Language language;

    @Column(length = 256, nullable = false)
    private String name;

    @Column(length = 512)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
