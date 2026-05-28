package dev.animedia.contentservice.domain.genre.model;

import dev.animedia.contentservice.domain.genre.exception.GenreTranslationNameRequiredException;
import dev.animedia.contentservice.domain.shared.translation.model.BaseTranslation;

public class GenreTranslation extends BaseTranslation {
    private final Long id;
    private String name;
    private String description;

    public GenreTranslation(Long id, String languageCode, String name, String description) {
        validateName(name);
        this.id = id;
        setLanguageCode(languageCode);
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void update(String name, String description) {
        validateName(name);
        this.name = name;
        this.description = description;
    }

    public void validateName(String name) {
        if (name == null || name.isBlank()) throw new GenreTranslationNameRequiredException();
    }
}
