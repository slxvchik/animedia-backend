package dev.animedia.contentservice.contentstatus.model;

import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;

@Entity
@Table(
    name = "content_status_translation",
    indexes = {
        @Index(name = "idx_content_status_translation_content_status_id_language_code", columnList = "content_status_id,language_code"),
        @Index(name = "idx_content_status_translation_language_code", columnList = "language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_content_status_translation_content_status_id_language_code",
            columnNames = {"content_status_id", "language_code"}
        )
    }
)
public class ContentStatusTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_status_id", nullable = false)
    private ContentStatus contentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_code", nullable = false)
    private Language language;

    @Column(length = 512, nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContentStatus getContentStatus() {
        return contentStatus;
    }

    public void setContentStatus(ContentStatus contentStatus) {
        this.contentStatus = contentStatus;
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
}