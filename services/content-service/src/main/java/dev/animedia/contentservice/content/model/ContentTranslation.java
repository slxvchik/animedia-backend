package dev.animedia.contentservice.content.model;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
    name = "content_translation",
    indexes = {
        @Index(name = "idx_content_translation_content_uuid_language_code", columnList = "content_uuid,language_code"),
        @Index(name = "idx_content_translation_language_code", columnList = "language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_content_translation_content_uuid_language_code",
            columnNames = {"content_uuid", "language_code"}
        )
    }
)
public class ContentTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_uuid", nullable = false)
    private Content content;

    @Column(name = "language_code", nullable = false)
    private String languageCode;

    @Column(length = 512, nullable = false)
    private String title;

    @Column(length = 32768)
    private String description;

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

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}