package dev.animedia.contentservice.comicchapterimage;

import dev.animedia.contentservice.comicchapter.core.ComicChapter;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(
    name = "comic_chapter_image",
    check = @CheckConstraint(
        name = "comic_chapter_image_page_positive",
        constraint = "page > 0"
    )
)
public class ComicChapterImage {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comic_chapter_id")
    private ComicChapter comicChapter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;

    @Column(nullable = false)
    private Integer page;

    @Column(name = "image_url", length = 512, unique = true)
    private String imageUrl;
}