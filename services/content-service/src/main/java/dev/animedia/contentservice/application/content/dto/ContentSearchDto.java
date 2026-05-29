package dev.animedia.contentservice.application.content.dto;

import dev.animedia.contentservice.domain.content.model.ContentType;
import jakarta.annotation.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record ContentSearchDto(
	@Nullable UUID uuid,
	@Nullable List<String> aliasList,
	@Nullable List<String> titleList,
	@Nullable List<ContentType> typeList,
	@Nullable List<Integer> seasonList,
	@Nullable List<UUID> statusIdList,
	@Nullable LocalDate releaseDateFrom,
	@Nullable LocalDate releaseDateTo,
	@Nullable LocalDateTime createdAtFrom,
	@Nullable LocalDateTime createdAtTo,
	@Nullable LocalDateTime updatedAtFrom,
	@Nullable LocalDateTime updatedAtTo,
	@Nullable Boolean active,
	@Nullable List<String> languageCodeList,
	@Nullable List<UUID> genreIdList,
	@Nullable String translateLanguageCode
) {}