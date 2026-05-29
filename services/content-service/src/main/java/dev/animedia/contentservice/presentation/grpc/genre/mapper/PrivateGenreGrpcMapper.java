package dev.animedia.contentservice.presentation.grpc.genre.mapper;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreSearchDto;
import dev.animedia.contentservice.application.genre.dto.GenreTranslationDto;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.genre.PrivateGenreProto.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PrivateGenreGrpcMapper {
	public GenreSearchDto toGenreSearchDto(
		PrivateSearchGenreRequest request
	) {
		if (request == null) return new GenreSearchDto(null, null, null, null, null);

		return new GenreSearchDto(
			request.hasActive() ? request.getActive() : null,
			request.hasAlias() ? request.getAlias() : null,
			request.hasName() ? request.getName() : null,
			request.hasDescription() ? request.getDescription() : null,
			request.hasLanguageCode() ? request.getLanguageCode() : null
		);
	}

	public GenreDto toGenreDto(
		CreateGenreRequest request
	) {
		if (request == null) return null;

		Set<GenreTranslationDto> translations =
			request.getTranslationsList()
				.stream()
				.map(this::toGenreTranslationDto)
				.filter(Objects::nonNull)
				.collect(Collectors.toSet());

		return new GenreDto(
			null,
			request.getAlias(),
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	private GenreTranslationDto toGenreTranslationDto(
		CreateGenreTranslationRequest request
	) {
		if (request == null) return null;

		return new GenreTranslationDto(
			null,
			request.getLanguageCode(),
			request.getName(),
			request.getDescription()
		);
	}

	public GenreDto toGenreDto(
		UpdateGenreRequest request
	) {
		if (request == null) return null;

		Set<GenreTranslationDto> translations =
			request.getTranslationsList()
				.stream()
				.map(this::toGenreTranslationDto)
				.collect(Collectors.toSet());

		return new GenreDto(
			UUID.fromString(request.getUuid()),
			null,
			request.getSortOrder(),
			request.getActive(),
			translations
		);
	}

	private GenreTranslationDto toGenreTranslationDto(
		UpdateGenreTranslationRequest request
	) {
		if (request == null) return null;

		return new GenreTranslationDto(
			request.hasUuid() ? UUID.fromString(request.getUuid()) : null,
			request.getLanguageCode(),
			request.getName(),
			request.hasDescription() ? request.getDescription() : null
		);
	}

	public PrivateSearchGenreResponse toPrivateSearchGenreResponse(
		List<PrivateGenreResponse> genreResponseList,
		PaginationResponse paginationResponse
	) {
		return PrivateSearchGenreResponse
			.newBuilder()
			.addAllGenres(genreResponseList)
			.setPagination(paginationResponse)
			.build();
	}

	public PrivateGenreResponse toPrivateGenreResponse(
		GenreDto genreDto
	) {
		if (genreDto == null) return null;

		List<PrivateGenreTranslationResponse> translations = genreDto.translationSet() != null
			? genreDto.translationSet()
				.stream()
				.map(gtd -> toPrivateGenreTranslationResponse(gtd, genreDto.id()))
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return PrivateGenreResponse
			.newBuilder()
			.setUuid(String.valueOf(genreDto.id()))
			.setAlias(genreDto.alias())
			.setSortOrder(genreDto.sortOrder())
			.setActive(genreDto.active())
			.addAllTranslations(translations)
			.build();
	}

	private PrivateGenreTranslationResponse toPrivateGenreTranslationResponse(
		GenreTranslationDto genreTranslationDto,
		UUID genreId
	) {
		if (genreTranslationDto == null) return null;

		return PrivateGenreTranslationResponse
			.newBuilder()
			.setUuid(String.valueOf(genreTranslationDto.id()))
			.setGenreUuid(String.valueOf(genreId))
			.setLanguageCode(genreTranslationDto.languageCode())
			.setName(genreTranslationDto.name())
			.setDescription(genreTranslationDto.description())
			.build();
	}
}
