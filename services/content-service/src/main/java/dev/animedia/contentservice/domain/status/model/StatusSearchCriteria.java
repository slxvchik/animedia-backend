package dev.animedia.contentservice.domain.status.model;

import jakarta.annotation.Nullable;

import java.util.List;

public record StatusSearchCriteria(
    @Nullable List<String> aliasList,
    @Nullable List<String> nameList,
    @Nullable List<String> languageCodeList
) {}