package dev.animedia.contentservice.infrastructure.status.persistence.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "status_translation",
    indexes = {
        @Index(name = "idx_status_translation_status_id_language_code", columnList = "status_id,language_code"),
        @Index(name = "idx_status_translation_language_code", columnList = "language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_status_translation_status_id_language_code",
            columnNames = {"status_id", "language_code"}
        )
    }
)
public class StatusTranslationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", nullable = false)
    private StatusEntity statusEntity;

    @Column(name = "language_code", nullable = false)
    private String languageCode;

    @Column(length = 512, nullable = false)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEntity getStatusEntity() {
        return statusEntity;
    }

    public void setStatusEntity(StatusEntity statusEntity) {
        this.statusEntity = statusEntity;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
