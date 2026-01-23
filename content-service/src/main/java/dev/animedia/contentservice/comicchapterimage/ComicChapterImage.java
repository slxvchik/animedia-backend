package dev.animedia.contentservice.comicchapterimage;

import dev.animedia.contentservice.comicchapter.core.ComicChapter;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(
    name = "comic_chapter_image",
    indexes = {
        @Index(name = "idx_comic_chapter_image_comic_chapter_uuid_language_code", columnList = "comic_chapter_uuid,language_code"),
        @Index(name = "idx_comic_chapter_image_language_code", columnList = "language_code")
    },
    check = @CheckConstraint(
        name = "comic_chapter_image_page_positive",
        constraint = "page > 0"
    ),
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uidx_comic_chapter_image_comic_chapter_uuid_language_id_image_url",
            columnNames = {
                "comic_chapter_uuid",
                "language_id",
                "image_url"
            }
        )
    }
)
public class ComicChapterImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_chapter_uuid", nullable = false)
    private ComicChapter comicChapter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;

    @Column(name = "image_url", length = 512, nullable = false, unique = true)
    private String imageUrl;

    @Column(nullable = false)
    private Integer page;

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

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}