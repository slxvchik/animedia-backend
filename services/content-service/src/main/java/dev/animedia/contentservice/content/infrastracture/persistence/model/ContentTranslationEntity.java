package dev.animedia.contentservice.content.infrastracture.persistence.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(
    name = "content_translation",
    indexes = {
        @Index(name = "idx_content_translation_content_id_language_code", columnList = "content_id,language_code"),
        @Index(name = "idx_content_translation_language_code", columnList = "language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_content_translation_content_id_language_code",
            columnNames = {"content_id", "language_code"}
        )
    }
)
public class ContentTranslationEntity {
    @Id
    private UUID id;

    @PrePersist
    private void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "content_id", nullable = false, updatable = false)
    private ContentEntity contentEntity;

    @Column(name = "language_code", nullable = false, updatable = false)
    private String languageCode;

    @Column(name = "title", length = 512, nullable = false)
    private String title;

    @Column(name = "description", length = 32768)
    private String description;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public ContentEntity getContentEntity() {
        return contentEntity;
    }

    public void setContentEntity(ContentEntity contentEntity) {
        this.contentEntity = contentEntity;
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

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ContentTranslationEntity that)) return false;
        return this.getContentEntity().getId().equals(that.getContentEntity().getId()) && languageCode.equals(that.languageCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getContentEntity().getId(), languageCode);
    }
}
