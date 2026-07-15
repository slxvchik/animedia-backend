package dev.animedia.contentservice.genre.domain.model;

import dev.animedia.contentservice.shared.domain.slugalias.SlugAlias;

import java.util.*;

public class Genre {
    private final UUID id;
    private final SlugAlias alias;
    private Integer sortOrder;
    private Boolean active;
    private final Set<GenreTranslation> translations = new HashSet<>();

    public Genre(UUID id, String alias, Integer sortOrder, Boolean active, Set<GenreTranslation> translations) {
        this.id = id;
        this.alias = new SlugAlias(alias);
        setSortOrder(sortOrder);
        setActive(active);
        setTranslations(translations);
    }

    public UUID getId() {
        return id;
    }

    public String getAlias() {
        return alias.getValue();
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public Boolean getActive() {
        return active;
    }

    public Set<GenreTranslation> getTranslations() {
        return Collections.unmodifiableSet(translations);
    }

    public void update(Integer sortOrder, Boolean active, Set<GenreTranslation> translations) {
        setSortOrder(sortOrder);
        setActive(active);
        setTranslations(translations);
    }

    private void setTranslations(Set<GenreTranslation> translations) {
        this.translations.clear();
        this.translations.addAll(translations);
    }

    private void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder != null ? Math.max(sortOrder, 0) : 0;
    }

    private void setActive(Boolean active) {
        this.active = active != null && active;
    }
}
