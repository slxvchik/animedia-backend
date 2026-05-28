package dev.animedia.contentservice.domain.content.model;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.status.model.Status;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

public record ContentUpdate(
	Status status,
	UUID coverImageId,
	UUID trailerVideoId,
	LocalDate releaseDate,
	boolean active,
	int sort,
	Set<String> languageCodeSet,
	Set<Genre> genreSet,
	Set<ContentTranslation> translationSet
) {}