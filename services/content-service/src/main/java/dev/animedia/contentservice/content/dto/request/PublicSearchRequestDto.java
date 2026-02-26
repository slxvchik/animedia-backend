package dev.animedia.contentservice.content.dto.request;

import dev.animedia.contentservice.content.model.ContentType;
import dev.animedia.contentservice.status.model.ContentStatus;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public record PublicSearchRequestDto(
	String alias,
	ContentType type,
	List<Integer> seasons,
	List<ContentStatus> contentStatuses,
	LocalDate releaseFrom,
	LocalDate releaseTo,
	List<String> languageCodes,
	SortBy sortBy,
	Pageable pageable
) {
	enum SortBy {
		RELEASE_DATE,
		SORT
	}
}
