package dev.animedia.contentservice.content.domain.model;

import dev.animedia.contentservice.genre.domain.model.Genre;
import dev.animedia.contentservice.status.domain.model.Status;

import java.time.LocalDate;
import java.util.Set;

public record ContentUpdate(
	Status status,
	String coverImageId,
	String trailerVideoId,
	LocalDate releaseDate,
	boolean active,
	int sort,
	Set<String> languageCodeSet,
	Set<Genre> genreSet,
	Set<ContentTranslation> translationSet
) {}