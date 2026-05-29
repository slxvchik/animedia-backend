package dev.animedia.contentservice.domain.genre.model;

import dev.animedia.contentservice.domain.genre.exception.GenreAliasRequiredException;
import dev.animedia.contentservice.domain.genre.exception.GenreInvalidAliasException;

import java.util.*;
import java.util.regex.Pattern;

public class Genre {
    private final UUID id;
    private final String alias;
    private int sortOrder;
    private boolean active;
    private final Set<GenreTranslation> translationSet = new HashSet<>();

    private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

    public Genre(UUID id, String alias, int sortOrder, boolean active, Set<GenreTranslation> translationSet) {
        validateAlias(alias);
        this.id = id;
        this.alias = alias;
        setSortOrder(sortOrder);
        this.active = active;
        setTranslationSet(translationSet);
    }

    public UUID getId() {
        return id;
    }

    public String getAlias() {
        return alias;
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

    private void validateAlias(String alias) {
        if (alias == null || alias.isBlank()) throw new GenreAliasRequiredException();
        if (!ALIAS_PATTERN.matcher(alias).matches()) throw new GenreInvalidAliasException();
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
