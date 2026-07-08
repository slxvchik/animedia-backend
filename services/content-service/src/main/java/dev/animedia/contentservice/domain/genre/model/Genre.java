package dev.animedia.contentservice.domain.genre.model;

import dev.animedia.contentservice.domain.shared.slugalias.SlugAlias;

import java.util.*;

public class Genre {
    private final UUID id;
    private final SlugAlias alias;
    private int sortOrder;
    private boolean active;
    private final Set<GenreTranslation> translationSet = new HashSet<>();

    public Genre(UUID id, String alias, int sortOrder, boolean active, Set<GenreTranslation> translationSet) {
        this.id = id;
        this.alias = new SlugAlias(alias);
        setSortOrder(sortOrder);
        this.active = active;
        setTranslationSet(translationSet);
    }

    public UUID getId() {
        return id;
    }

    public String getAlias() {
        return alias.getValue();
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public boolean getActive() {
        return active;
    }

    public Set<GenreTranslation> getTranslationSet() {
        return Collections.unmodifiableSet(translationSet);
    }

    public void update(int sortOrder, boolean active, Set<GenreTranslation> translationSet) {
        setSortOrder(sortOrder);
        this.active = active;
        setTranslationSet(translationSet);
    }

    private void setTranslationSet(Set<GenreTranslation> translationSet) {
        this.translationSet.clear();
        this.translationSet.addAll(translationSet);
    }

    private void setSortOrder(int sortOrder) {
        this.sortOrder = Math.max(sortOrder, 0);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Genre genre)) return false;
        return alias.equals(genre.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hash(alias);
    }
}
