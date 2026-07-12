package dev.animedia.contentservice.genre.presentation.mapper.user;

import dev.animedia.contentservice.genre.application.dto.GenreDto;
import dev.animedia.contentservice.genre.application.dto.GenreTranslationDto;
import dev.animedia.grpc.genre.user.v1.GenreUserProto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class GenreUserMapperGrpc {
	public GenreUserProto.GenreResponse toGenreGrpcResponse(
		GenreDto genreDto
	) {
		if (genreDto == null) return null;

		List<GenreUserProto.GenreTranslationResponse> translations = genreDto.translationSet() != null
			? genreDto.translationSet()
				.stream()
				.map(this::toGenreTranslationGrpcResponse)
				.filter(Objects::nonNull)
				.toList()
			: List.of();

		return GenreUserProto.GenreResponse
			.newBuilder()
			.setAlias(genreDto.alias())
			.setSortOrder(genreDto.sortOrder())
			.addAllTranslations(translations)
			.build();
	}

	private GenreUserProto.GenreTranslationResponse toGenreTranslationGrpcResponse(
		GenreTranslationDto genreTranslationDto
	) {
		if (genreTranslationDto == null) return null;

		return GenreUserProto.GenreTranslationResponse
			.newBuilder()
			.setLanguageCode(genreTranslationDto.languageCode())
			.setName(genreTranslationDto.name())
			.setDescription(genreTranslationDto.description())
			.build();
	}
}
