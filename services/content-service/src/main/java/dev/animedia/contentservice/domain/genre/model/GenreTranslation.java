package dev.animedia.contentservice.domain.genre.model;

import dev.animedia.contentservice.domain.genre.exception.GenreTranslationLanguageCodeRequiredException;
import dev.animedia.contentservice.domain.genre.exception.GenreTranslationNameRequiredException;
import dev.animedia.contentservice.domain.shared.model.BaseTranslationEntity;

public class GenreTranslation extends BaseTranslationEntity {
    private final Long id;
    private String name;
    private String description;

    public GenreTranslation(Long id, String languageCode, String name, String description) {
        validateLanguageCode(languageCode);
        validateName(name);
        this.id = id;
        this.languageCode = languageCode;
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

    public void validateLanguageCode(String languageCode) {
        if (languageCode == null || languageCode.isBlank()) throw new GenreTranslationLanguageCodeRequiredException();
    }

    public void validateName(String name) {
        if (name == null || name.isBlank()) throw new GenreTranslationNameRequiredException();
    }
}
