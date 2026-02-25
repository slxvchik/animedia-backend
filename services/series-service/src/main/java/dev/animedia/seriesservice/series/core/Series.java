package dev.animedia.contentservice.series.core;

import dev.animedia.contentservice.content.core.Content;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
    indexes = {
        @Index(name = "idx_series_content_uuid", columnList = "content_uuid")
    },
    check = {
        @CheckConstraint(
            name = "series_duration_positive",
            constraint = "duration > 0"
        ),
        @CheckConstraint(
            name = "series_episode_positive",
            constraint = "episode > 0"
        )
    }
)
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_uuid", nullable = false)
    private Content content;

    @Column(nullable = false)
    private boolean active = true;

    @Column
    private Integer duration;

    @Column(nullable = false)
    private Integer episode;

    @Column
    private LocalDate releaseDate;

    @Column(length = 512, nullable = false)
    private String videoUrl;

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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
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

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}