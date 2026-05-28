package dev.animedia.contentservice.presentation.grpc.genre.mapper;

import dev.animedia.contentservice.application.genre.dto.GenreDto;
import dev.animedia.contentservice.application.genre.dto.GenreSearchDto;
import dev.animedia.contentservice.application.genre.dto.GenreTranslationDto;
import dev.animedia.grpc.common.CommonProto.PaginationResponse;
import dev.animedia.grpc.genre.PublicGenreProto.PublicGenreResponse;
import dev.animedia.grpc.genre.PublicGenreProto.PublicGenreTranslationResponse;
import dev.animedia.grpc.genre.PublicGenreProto.PublicSearchGenreRequest;
import dev.animedia.grpc.genre.PublicGenreProto.PublicSearchGenreResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PublicGenreGrpcMapper {
	public GenreSearchDto toGenreSearchDto(
		PublicSearchGenreRequest request,
		String languageCode
	) {
		return new GenreSearchDto(
			true,
			request.hasAlias() ? request.getAlias() : null,
			request.hasName() ? request.getName() : null,
			null,
			languageCode
		);
	}

	public PublicSearchGenreResponse toPublicSearchGenreResponse(
		List<PublicGenreResponse> genreResponseList,
		PaginationResponse paginationResponse
	) {
		return PublicSearchGenreResponse
			.newBuilder()
			.addAllGenres(genreResponseList)
			.setPagination(paginationResponse)
			.build();
	}

	public PublicGenreResponse toPublicGenreResponse(
		GenreDto genreDto
	) {
		List<PublicGenreTranslationResponse> translations = genreDto.translationSet() != null
			? genreDto.translationSet()
				.stream()
				.map(this::toPublicGenreTranslationResponse)
				.toList()
			: List.of();

		return PublicGenreResponse
			.newBuilder()
			.setAlias(genreDto.alias())
			.addAllTranslations(translations)
			.build();
	}

	private PublicGenreTranslationResponse toPublicGenreTranslationResponse(
		GenreTranslationDto genreTranslationDto
	) {
		return PublicGenreTranslationResponse
			.newBuilder()
			.setName(genreTranslationDto.name())
			.setDescription(genreTranslationDto.description())
			.build();
	}
}
