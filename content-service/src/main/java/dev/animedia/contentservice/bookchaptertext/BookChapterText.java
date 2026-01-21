package dev.animedia.contentservice.bookchaptertext;

import dev.animedia.contentservice.bookchapter.BookChapter;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
    name = "book_chapter_text",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_book_chapter_text_book_chapter_id_language_code",
            columnNames = {"book_chapter_id", "language_code"}
        )
    }
)
public class BookChapterText {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_chapter_id")
    private BookChapter bookChapter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_code")
    private Language language;

    @Column(columnDefinition = "TEXT")
    private String text;
}
