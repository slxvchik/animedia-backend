package dev.animedia.contentservice.content.dto.request;

import dev.animedia.contentservice.content.model.ContentType;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PrivateSearchRequestDto(
	UUID uuid,
	String alias,
	String title,
	List<ContentType> types,
	List<Integer> seasons,
	List<Long> contentStatusIds,
	LocalDate releaseFrom,
	LocalDate releaseTo,
	LocalDateTime createdAtFrom,
	LocalDateTime createdAtTo,
	LocalDateTime updatedAtFrom,
	LocalDateTime updatedAtTo,
	Boolean active,
	List<String> languageCodes,
	List<Long> genreIds
) implements CommonSearchRequestDto {}
