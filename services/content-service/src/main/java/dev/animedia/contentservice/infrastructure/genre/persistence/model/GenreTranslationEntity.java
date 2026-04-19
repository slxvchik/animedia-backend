package dev.animedia.contentservice.infrastructure.genre.persistence.model;

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
public class GenreTranslationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", nullable = false)
    private GenreEntity genreEntity;

    @Column(name = "language_code", nullable = false)
    private String languageCode;

    @Column(length = 512, nullable = false)
    private String name;

    @Column(length = 2048)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GenreEntity getGenreEntity() {
        return genreEntity;
    }

    public void setGenreEntity(GenreEntity genreEntity) {
        this.genreEntity = genreEntity;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
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
