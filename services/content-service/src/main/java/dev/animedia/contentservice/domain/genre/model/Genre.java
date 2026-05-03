package dev.animedia.contentservice.domain.genre.model;

import dev.animedia.contentservice.domain.genre.exception.GenreAliasRequiredException;
import dev.animedia.contentservice.domain.genre.exception.GenreInvalidAliasException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

public class Genre {
    private final Long id;
    private final String alias;
    private int sortOrder;
    private final Set<GenreTranslation> translationSet = new HashSet<>();

    private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

    public Genre(Long id, String alias, int sortOrder, Set<GenreTranslation> translationSet) {
        validateAlias(alias);
        this.id = id;
        this.alias = alias;
        setSortOrder(sortOrder);
        setTranslationSet(translationSet);
    }

    public Long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public Set<GenreTranslation> getTranslationSet() {
        return Collections.unmodifiableSet(translationSet);
    }

    public void update(int sortOrder, Set<GenreTranslation> translationSet) {
        setSortOrder(sortOrder);
        setTranslationSet(translationSet);
    }

    public void removeTranslation(Long id) {
        this.translationSet.removeIf(translation -> translation.getId().equals(id));
    }

    private void validateAlias(String alias) {
        if (alias == null || alias.isBlank()) throw new GenreAliasRequiredException();
        if (ALIAS_PATTERN.matcher(alias).hasMatch()) throw new GenreInvalidAliasException();
    }

    private void setTranslationSet(Set<GenreTranslation> translationSet) {
        if (translationSet != null) {
            this.translationSet.retainAll(translationSet);
            this.translationSet.addAll(translationSet);
        } else {
            this.translationSet.clear();
        }
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
