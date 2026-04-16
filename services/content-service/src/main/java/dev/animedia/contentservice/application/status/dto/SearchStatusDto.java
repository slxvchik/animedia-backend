package dev.animedia.contentservice.application.status.dto;

import jakarta.annotation.Nullable;

import java.util.List;

public record SearchStatusDto(
    @Nullable List<String> aliasList,
    @Nullable List<String> nameList,
    @Nullable List<String> languageCodeList
) {}