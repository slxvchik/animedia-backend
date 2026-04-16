package dev.animedia.contentservice.application.genre.dto;

import jakarta.annotation.Nullable;

import java.util.List;

public record SearchGenreDto(
	@Nullable List<String> aliasList,
	@Nullable List<String> nameList,
	@Nullable String description,
	@Nullable List<String> languageCodeList
) {}