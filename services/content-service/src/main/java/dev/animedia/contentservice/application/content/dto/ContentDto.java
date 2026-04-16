package dev.animedia.contentservice.application.content.dto;

import dev.animedia.contentservice.domain.content.model.ContentTranslation;
import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.status.model.Status;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record ContentDto(
	UUID uuid,
	String alias,
	ContentType type,
	int season,
	Status status,
	String coverUrl,
	String trailerUrl,
	LocalDate releaseDate,
	boolean active,
	int sort,
	Set<String>languageCodeSet,
	Set<Genre> genreSet,
	Set<ContentTranslation> translationSet
) {}