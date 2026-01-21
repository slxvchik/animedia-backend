package dev.animedia.contentservice.comicchapter.core;

import dev.animedia.contentservice.content.core.Content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
    name = "comic_chapter",
    check = {
        @CheckConstraint(
            name = "comic_chapter_pages_positive",
            constraint = "pages > 0"
        ),
        @CheckConstraint(
            name = "comic_chapter_episode_positive",
            constraint = "episode > 0"
        )
    }
)
public class ComicChapter {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id")
    private Content content;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private Integer episode;

    @Column
    private boolean active = true;

    @Column
    private LocalDate releaseDate;
}