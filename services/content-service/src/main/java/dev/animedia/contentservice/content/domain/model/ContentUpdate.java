package dev.animedia.contentservice.content.domain.model;

import java.time.LocalDate;
import java.util.Set;

public record ContentUpdate(
	String statusId,
	String coverImageId,
	String trailerVideoId,
	LocalDate releaseDate,
	boolean active,
	int sort,
	Set<String> languageCodeSet,
	Set<String> genreIdSet,
	Set<ContentTranslation> translationSet
) {}