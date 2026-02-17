package dev.animedia.contentservice.bookchaptertext;

import dev.animedia.contentservice.bookchapter.core.BookChapter;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
    name = "book_chapter_text",
    indexes = {
        @Index(name = "idx_book_chapter_text_book_chapter_uuid_language_code", columnList = "book_chapter_uuid,language_code"),
        @Index(name = "idx_book_chapter_text_language_code", columnList = "language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_book_chapter_text_book_chapter_uuid_language_code",
            columnNames = {"book_chapter_uuid", "language_code"}
        )
    }
)
public class BookChapterText {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_chapter_uuid")
    private BookChapter bookChapter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_code")
    private Language language;

    @Column(name = "text_url", length = 512, nullable = false, unique = true)
    private String textUrl;

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

    public String getTextUrl() {
        return textUrl;
    }

    public void setTextUrl(String textUrl) {
        this.textUrl = textUrl;
    }
}
