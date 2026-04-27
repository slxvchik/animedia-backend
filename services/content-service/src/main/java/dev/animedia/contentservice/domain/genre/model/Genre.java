package dev.animedia.contentservice.domain.genre.model;

import dev.animedia.contentservice.domain.genre.exception.GenreAliasRequiredException;
import dev.animedia.contentservice.domain.genre.exception.GenreInvalidAliasException;
import dev.animedia.contentservice.domain.shared.model.BaseEntity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Genre extends BaseEntity<Long> {
    private String alias;
    private long sortOrder;
    private Set<GenreTranslation> translationSet = new HashSet<>();

    private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

    public Genre(Long id, String alias, long sortOrder, Set<GenreTranslation> translationSet) {
        validateAlias(alias);
        this.id = id;
        this.alias = alias;
        setSortOrder(sortOrder);
        saveTranslations(translationSet);
    }

    public void update(String alias, long sortOrder, Set<GenreTranslation> translationSet) {
        validateAlias(alias);
        this.alias = alias;
        setSortOrder(sortOrder);
        saveTranslations(translationSet);
    }

    void validateAlias(String alias) {
        if (alias == null || alias.isBlank()) throw new GenreAliasRequiredException();
        if (ALIAS_PATTERN.matcher(alias).hasMatch()) throw new GenreInvalidAliasException();
    }

    public void removeTranslation(Long id) {
        this.translationSet.removeIf(translation -> translation.getId().equals(id));
    }

    private void saveTranslations(Set<GenreTranslation> translationSet) {
        if (translationSet != null) {
            this.translationSet.retainAll(translationSet);
            this.translationSet.addAll(translationSet);
        } else {
            this.translationSet.clear();
        }
    }

    private void setSortOrder(long sortOrder) {
        this.sortOrder = Math.max(sortOrder, 0);
    }

    public String getAlias() {
        return alias;
    }

    public long getSortOrder() {
        return sortOrder;
    }

    public Set<GenreTranslation> getTranslationSet() {
        return Collections.unmodifiableSet(translationSet);
    }
}
