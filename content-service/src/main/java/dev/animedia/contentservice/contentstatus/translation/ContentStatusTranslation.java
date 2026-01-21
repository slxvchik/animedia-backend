package dev.animedia.contentservice.contentstatus.translation;

import dev.animedia.contentservice.contentstatus.core.ContentStatus;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
    name = "content_status_translation",
    indexes = {
        @Index(name = "idx_content_status_translation_content_status_id_language_code", columnList = "content_status_id,language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_content_status_translation_content_status_id_language_code",
            columnNames = {"content_status_id", "language_code"}
        )
    }
)
public class ContentStatusTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        nullable = false,
        name = "content_status_id"
    )
    private ContentStatus contentStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        nullable = false,
        name = "language_code",
        referencedColumnName = "code"
    )
    private Language language;

    @Column(length = 512, nullable = false)
    private String name;
}