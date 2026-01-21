package dev.animedia.contentservice.movie;

import dev.animedia.contentservice.content.core.Content;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
    @JoinColumn(name = "content_id", nullable = false, unique = true)
    private Content content;

    @Column(name = "video_url", nullable = false, unique = true)
    private String videoUrl;

    @Column
    private Integer duration;
}