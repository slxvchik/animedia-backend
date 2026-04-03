package dev.animedia.contentservice.old.content.dto.request;

import dev.animedia.contentservice.old.content.model.ContentType;

import java.time.LocalDate;
import java.util.List;

public record PublicSearchRequestDto(
	String alias,
	String title,
	List<ContentType> types,
	List<Integer> seasons,
	List<Long> contentStatusIds,
	LocalDate releaseFrom,
	LocalDate releaseTo,
	List<String> languageCodes,
	List<Long> genreIds
) implements CommonSearchRequestDto {}
