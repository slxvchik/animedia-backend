package dev.animedia.contentservice.comicchapter.translation;

import dev.animedia.contentservice.comicchapter.core.ComicChapter;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
    name = "comic_chapter_translation",
    indexes = {
        @Index(name = "idx_comic_chapter_translation_comic_chapter_uuid_language_code", columnList = "comic_chapter_uuid,language_code"),
        @Index(name = "idx_comic_chapter_translation_language_code", columnList = "language_code")
    },
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_comic_chapter_translation_comic_chapter_uuid_language_code",
            columnNames = {"comic_chapter_uuid", "language_code"}
        )
    }
)
public class ComicChapterTranslation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_chapter_uuid", nullable = false)
    private ComicChapter comicChapter;

    @Column(name = "language_code", nullable = false)
    private String languageCode;

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

    public ComicChapter getComicChapter() {
        return comicChapter;
    }

    public void setComicChapter(ComicChapter comicChapter) {
        this.comicChapter = comicChapter;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}