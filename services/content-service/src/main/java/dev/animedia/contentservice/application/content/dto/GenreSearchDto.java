package dev.animedia.contentservice.application.content.dto;

import jakarta.annotation.Nullable;

import java.util.List;

public record GenreSearchDto(
	@Nullable List<String> aliasList,
	@Nullable List<String> nameList,
	@Nullable String description,
	@Nullable List<String> languageCodeList
) {}