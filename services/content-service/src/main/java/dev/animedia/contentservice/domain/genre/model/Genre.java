package dev.animedia.contentservice.domain.genre.model;

import dev.animedia.contentservice.domain.genre.exception.GenreAliasRequiredException;
import dev.animedia.contentservice.domain.genre.exception.GenreInvalidAliasException;

import java.util.Set;
import java.util.regex.Pattern;

public class Genre {
    private final long id;
    private String alias;
    private long sortOrder;
    private Set<GenreTranslation> translationSet;

    private final static Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

    public Genre(long id, String alias, long sortOrder, Set<GenreTranslation> translationSet) {
        validateAlias(alias);
        this.id = id;
        this.alias = alias;
        setSortOrder(sortOrder);
        this.translationSet = translationSet;
    }

    public void update(String alias, long sortOrder, Set<GenreTranslation> translationSet) {
        validateAlias(alias);
        this.alias = alias;
        setSortOrder(sortOrder);
        this.translationSet = translationSet;
    }

    void validateAlias(String alias) {
        if (alias == null || alias.isBlank()) throw new GenreAliasRequiredException();
        if (ALIAS_PATTERN.matcher(alias).hasMatch()) throw new GenreInvalidAliasException();
    }

    private void setSortOrder(long sortOrder) {
        this.sortOrder = Math.max(sortOrder, 0);
    }

    public long getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public long getSortOrder() {
        return sortOrder;
    }

    public Set<GenreTranslation> getTranslationSet() {
        return translationSet;
    }
}
