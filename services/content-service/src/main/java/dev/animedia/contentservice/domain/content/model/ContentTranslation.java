package dev.animedia.contentservice.domain.content.model;

import dev.animedia.contentservice.domain.content.exception.ContentTranslationTitleRequiredException;
import dev.animedia.contentservice.domain.content.exception.ContentTranslationLanguageCodeRequiredException;

import java.util.UUID;

public class ContentTranslation {
    private final UUID uuid;
    private final String languageCode;
    private String title;
    private String description;

    public ContentTranslation(UUID uuid, String languageCode, String title, String description) {
        validateLanguageCode(languageCode);
        validateTitle(title);
        this.uuid = uuid;
        this.languageCode = languageCode;
        this.title = title;
        this.description = description;
    }

    public void update(String title, String description) {
        validateTitle(title);
        this.title = title;
        this.description = description;
    }

    public void validateLanguageCode(String languageCode) {
        if (languageCode == null || languageCode.isBlank()) throw new ContentTranslationLanguageCodeRequiredException();
    }

    private void validateTitle(String title) {
        if (title == null) throw new ContentTranslationTitleRequiredException();
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}