package dev.animedia.contentservice.movie;

import dev.animedia.contentservice.content.core.Content;
import jakarta.persistence.*;

@Entity
@Table(
    check = @CheckConstraint(
        name = "movie_duration_positive",
        constraint = "duration > 0"
    )
)
public class Movie {
    @Id
    @OneToOne
    @JoinColumn(name = "content_uuid", nullable = false, unique = true)
    private Content content;

    @Column(name = "video_url", nullable = false, unique = true)
    private String videoUrl;

    @Column
    private Integer duration;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}