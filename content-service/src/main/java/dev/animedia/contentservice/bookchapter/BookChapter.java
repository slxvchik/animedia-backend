package dev.animedia.contentservice.bookchapter;

import dev.animedia.contentservice.content.core.Content;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
    name = "book_chapter",
    check = @CheckConstraint(
        name = "book_episode_positive",
        constraint = "episode > 0"
    )
)
public class BookChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_code", nullable = false)
    private Language language;

    @Column
    private Boolean active = true;

    @Column(nullable = false)
    private Integer episode;

    @Column(length = 4096)
    private String name;

    @Column(length = 16386)
    private String description;

    @Column(name = "release_date")
    private LocalDate releaseDate;
}
