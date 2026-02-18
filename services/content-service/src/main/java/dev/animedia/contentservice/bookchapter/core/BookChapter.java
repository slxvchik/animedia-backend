package dev.animedia.contentservice.bookchapter.core;

import dev.animedia.contentservice.content.core.Content;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
    name = "book_chapter",
    indexes = {
        @Index(name = "idx_book_chapter_content_uuid", columnList = "content_uuid")
    },
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
    @JoinColumn(name = "content_uuid", nullable = false)
    private Content content;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(nullable = false)
    private Integer episode;

    @Column(name = "release_date")
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getEpisode() {
        return episode;
    }

    public void setEpisode(Integer episode) {
        this.episode = episode;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }
}
