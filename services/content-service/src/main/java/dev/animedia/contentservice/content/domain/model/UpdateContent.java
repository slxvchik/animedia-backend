package dev.animedia.contentservice.content.domain.model;

import java.time.LocalDate;
import java.util.Set;

public record UpdateContent(
	String statusId,
	String coverImageId,
	String trailerVideoId,
	LocalDate releaseDate,
	Boolean active,
	Integer sort,
	Set<String> languageCodes,
	Set<String> genreIds,
	Set<ContentTranslation> translations
) {}