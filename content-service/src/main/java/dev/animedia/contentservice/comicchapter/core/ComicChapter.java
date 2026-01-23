package dev.animedia.contentservice.comicchapter.core;

import dev.animedia.contentservice.content.core.Content;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
    name = "comic_chapter",
    indexes = {
        @Index(name = "idx_comic_chapter_content_uuid", columnList = "content_uuid")
    },
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
    @JoinColumn(name = "content_uuid", nullable = false)
    private Content content;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false)
    private Integer episode;

    @Column(nullable = false)
    private boolean active = true;

    @Column
    private LocalDate releaseDate;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}