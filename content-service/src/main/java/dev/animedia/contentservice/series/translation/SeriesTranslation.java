package dev.animedia.contentservice.series.translation;

import dev.animedia.contentservice.language.Language;
import dev.animedia.contentservice.series.core.Series;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
    name = "series_translation",
    indexes = {
        @Index(name = "idx_series_translation_series_uuid_language_code", columnList = "series_uuid,language_code"),
        @Index(name = "idx_series_translation_language_code", columnList = "language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_series_translation_series_uuid_language_code",
            columnNames = {"series_uuid", "language_code"}
        )
    }
)
public class SeriesTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_uuid", nullable = false)
    private Series series;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_code", nullable = false)
    private Language language;

    @Column(length = 2048)
    private String name;

    @Column(length = 16384)
    private String description;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Series getSeries() {
        return series;
    }

    public void setSeries(Series series) {
        this.series = series;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}