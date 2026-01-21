package dev.animedia.contentservice.genre.translation;

import dev.animedia.contentservice.genre.core.Genre;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    indexes = {
        @Index(name = "idx_genre_translation_lookup", columnList = "genre_id,language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(name = "unique_genre_translation_genre_id_language_code", columnNames = {"genre_id", "language_code"})
    }
)
public class GenreTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_code")
    private Language language;

    @Column(length = 200, nullable = false)
    private String name;
    @Column(length = 512)
    private String description;
}
