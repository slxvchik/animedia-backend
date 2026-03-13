package dev.animedia.contentservice.content.dto.request;

import dev.animedia.contentservice.content.model.ContentType;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public record PublicSearchRequestDto(
	String alias,
	String title,
	ContentType type,
	List<Integer> seasons,
	List<Long> contentStatusIds,
	LocalDate releaseFrom,
	LocalDate releaseTo,
	List<String> languageCodes,
	List<Long> genreIds
) implements CommonSearchRequestDto {}
