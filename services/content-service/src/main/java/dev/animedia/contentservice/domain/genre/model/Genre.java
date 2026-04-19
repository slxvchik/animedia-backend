package dev.animedia.contentservice.domain.genre.model;

import dev.animedia.contentservice.domain.genre.exception.GenreAliasRequiredException;
import dev.animedia.contentservice.domain.genre.exception.GenreInvalidAliasException;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class Genre {
    private final Long id;
    private String alias;
    private long sortOrder;
    private Set<GenreTranslation> translationSet = new HashSet<>();

    private static final Pattern ALIAS_PATTERN = Pattern.compile("^[a-z]{2,10}(?:-[a-z]{1,10}){0,8}$");

    public Genre(Long id, String alias, long sortOrder, Set<GenreTranslation> translationSet) {
        validateAlias(alias);
        this.id = id;
        this.alias = alias;
        setSortOrder(sortOrder);
        if (translationSet != null) {
            this.translationSet = translationSet;
        }
    }

    public void update(String alias, long sortOrder, Set<GenreTranslation> translationSet) {
        validateAlias(alias);
        this.alias = alias;
        setSortOrder(sortOrder);
        if (translationSet != null) {
            this.translationSet.clear();
            this.translationSet.addAll(translationSet);
        }
    }

    void validateAlias(String alias) {
        if (alias == null || alias.isBlank()) throw new GenreAliasRequiredException();
        if (ALIAS_PATTERN.matcher(alias).hasMatch()) throw new GenreInvalidAliasException();
    }

    public void saveTranslation(GenreTranslation genreTranslation) {
        this.translationSet.stream()
            .filter(translation -> translation.getLanguageCode().equals(genreTranslation.getLanguageCode()))
            .findFirst()
            .ifPresentOrElse(
                existing -> existing.update(genreTranslation.getName(), genreTranslation.getDescription()),
                () -> this.translationSet.add(
                    new GenreTranslation(null, genreTranslation.getLanguageCode(), genreTranslation.getName(), genreTranslation.getDescription())
                )
            );
    }

    public void removeTranslation(Long id) {
        this.translationSet.removeIf(translation -> translation.getId().equals(id));
    }

    private void setSortOrder(long sortOrder) {
        this.sortOrder = Math.max(sortOrder, 0);
    }

    public Long getId() {
        return id;
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
