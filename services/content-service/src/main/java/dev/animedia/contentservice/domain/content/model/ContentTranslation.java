package dev.animedia.contentservice.domain.content.model;

import dev.animedia.contentservice.domain.content.exception.ContentTranslationTitleRequiredException;
import dev.animedia.contentservice.domain.shared.translation.model.BaseTranslation;

import java.util.UUID;

public class ContentTranslation extends BaseTranslation {
    private final UUID id;
    private String title;
    private String description;

    public ContentTranslation(UUID id, String languageCode, String title, String description) {
        validateTitle(title);
        this.id = id;
        setLanguageCode(languageCode);
        this.title = title;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void update(String title, String description) {
        validateTitle(title);
        this.title = title;
        this.description = description;
    }

    private void validateTitle(String title) {
        if (title == null) throw new ContentTranslationTitleRequiredException();
    }
}