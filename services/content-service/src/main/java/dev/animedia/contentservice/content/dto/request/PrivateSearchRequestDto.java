package dev.animedia.contentservice.content.dto.request;

import dev.animedia.contentservice.content.model.ContentType;
import dev.animedia.contentservice.status.model.ContentStatus;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PrivateSearchRequestDto(
	UUID uuid,
	String alias,
	ContentType type,
	List<Integer> seasons,
	List<ContentStatus> contentStatuses,
	LocalDate releaseFrom,
	LocalDate releaseTo,
	LocalDateTime createdAtFrom,
	LocalDateTime createdAtTo,
	LocalDateTime updatedAtFrom,
	LocalDateTime updatedAtTo,
	Boolean active,
	List<String> languageCodes,
	SortBy sortBy,
	Pageable pageable
) {
	enum SortBy {
		RELEASE_DATE,
		CREATED_AT,
		UPDATED_AT,
		SORT
	}
}
