package dev.animedia.contentservice.domain.genre.model;

import jakarta.annotation.Nullable;

import java.util.List;

public record GenreSearchCriteria(
    @Nullable List<String> aliasList,
    @Nullable List<String> nameList,
    @Nullable String description,
    @Nullable List<String> languageCodeList
) {}