package dev.animedia.contentservice.series.core;

import dev.animedia.contentservice.content.core.Content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(
    indexes = {
        @Index(name = "idx_series_content_id", columnList = "content_id")
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
    @JoinColumn(name = "content_id")
    private Content content;

    @Column
    private boolean active = true;

    @Column
    private Integer duration;

    @Column(nullable = false)
    private Integer episode;

    @Column
    private LocalDate releaseDate;

    @Column(length = 512, nullable = false)
    private String videoUrl;
}