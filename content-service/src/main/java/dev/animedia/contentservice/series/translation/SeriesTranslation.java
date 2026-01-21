package dev.animedia.contentservice.series.translation;

import dev.animedia.contentservice.content.core.Content;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_series_translation_content_id_language_code",
            columnNames = {"content_id", "language_code"}
        )
    }
)
public class SeriesTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false)
    private Content content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_code", nullable = false)
    private Language language;

    @Column(length = 2048)
    private String name;

    @Column(length = 16384)
    private String description;
}