package dev.animedia.contentservice.bookchapter.translation;

import java.util.UUID;

import dev.animedia.contentservice.bookchapter.core.BookChapter;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;

@Entity
@Table(
    name = "book_chapter_translation",
    indexes = {
        @Index(name = "idx_book_chapter_translation_book_chapter_uuid_language_code", columnList = "book_chapter_uuid,language_code"),
        @Index(name = "idx_book_chapter_translation_language_code", columnList = "language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_book_chapter_translation_book_chapter_uuid_language_code",
            columnNames = {"book_chapter_uuid", "language_code"}
        )
    }
)
public class BookChapterTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_chapter_uuid", nullable = false)
    private BookChapter bookChapter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_code", nullable = false)
    private Language language;

    @Column(length = 4096)
    private String name;

    @Column(length = 16386)
    private String description;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public BookChapter getBookChapter() {
        return bookChapter;
    }

    public void setBookChapter(BookChapter bookChapter) {
        this.bookChapter = bookChapter;
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
