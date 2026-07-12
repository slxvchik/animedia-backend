package dev.animedia.contentservice.genre.domain.model;

import dev.animedia.contentservice.shared.domain.exception.FieldRequiredException;
import dev.animedia.contentservice.shared.domain.translation.model.BaseTranslation;

import java.util.UUID;

public class GenreTranslation extends BaseTranslation {
    private final UUID id;
    private String name;
    private String description;

    public GenreTranslation(UUID id, String languageCode, String name, String description) {
        validateName(name);
        this.id = id;
        setLanguageCode(languageCode);
        this.name = name;
        this.description = description;
    }

    public UUID getId() {
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
        if (name == null || name.isBlank()) throw new FieldRequiredException("genre translation: name");
    }
}
