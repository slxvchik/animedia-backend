package dev.animedia.contentservice.genre.mapper;

import dev.animedia.contentservice.genre.dto.request.GenreRequestDto;
import dev.animedia.contentservice.genre.dto.request.GenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.grpc.genre.GenreCommonProto;
import dev.animedia.grpc.genre.PrivateGenreProto;
import dev.animedia.grpc.genre.PublicGenreProto;
import dev.animedia.grpc.genre.PrivateGenreTranslationProto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GrpcGenreMapper {

    public PrivateGenreProto.PrivateSearchResponse toPrivateSearchResponse(
        List<GenreWithTranslationsResponseDto> genresWithTranslations,
        CommonProto.PaginationResponse pagination
    ) {

        List<GenreCommonProto.GenreWithTranslationsResponse> genres = new ArrayList<>();

        genresWithTranslations.forEach(genreResponseDto -> {

            GenreCommonProto.GenreResponse genre = GenreCommonProto.GenreResponse.newBuilder()
                .setId(genreResponseDto.id())
                .setAlias(genreResponseDto.alias())
                .setSort(genreResponseDto.sort())
                .build();

            List<GenreCommonProto.GenreTranslationResponse> translations = toProtoGenreTranslations(genreResponseDto.translations());
            var genreWithTranslations = GenreCommonProto.GenreWithTranslationsResponse.newBuilder()
                    .setGenre(genre)
                    .addAllTranslations(translations)
                    .build();

            genres.add(genreWithTranslations);
        });

        return PrivateGenreProto.PrivateSearchResponse.newBuilder()
            .addAllGenres(genres)
            .setPagination(pagination)
            .build();
    }

    public PublicGenreProto.PublicSearchResponse toPublicSearchResponse(List<GenreWithTranslationResponseDto> genresWithTranslation, CommonProto.PaginationResponse pagination) {
        List<GenreCommonProto.GenreWithTranslationResponse> genres = new ArrayList<>();

        genresWithTranslation.forEach(genreWithTranslationResponseDto -> {

            GenreCommonProto.GenreResponse genre = GenreCommonProto.GenreResponse.newBuilder()
                .setId(genreWithTranslationResponseDto.id())
                .setAlias(genreWithTranslationResponseDto.alias())
                .setSort(genreWithTranslationResponseDto.sort())
                .build();

            GenreCommonProto.GenreTranslationResponse translation = GenreCommonProto.GenreTranslationResponse.newBuilder()
                .setId(genreWithTranslationResponseDto.genreTranslationId())
                .setGenreId(genreWithTranslationResponseDto.id())
                .setLanguageCode(genreWithTranslationResponseDto.languageCode())
                .setName(genreWithTranslationResponseDto.name())
                .setDescription(genreWithTranslationResponseDto.description())
                .build();

            var genreWithTranslations = GenreCommonProto.GenreWithTranslationResponse.newBuilder()
                .setGenre(genre)
                .setTranslation(translation)
                .build();

            genres.add(genreWithTranslations);
        });

        return PublicGenreProto.PublicSearchResponse.newBuilder()
            .addAllGenres(genres)
            .setPagination(pagination)
            .build();
    }

    public List<GenreCommonProto.GenreTranslationResponse> toProtoGenreTranslations(List<GenreTranslationResponseDto> genreTranslationsResponseDto) {
        return genreTranslationsResponseDto.stream().map(this::toProtoGenreTranslation).toList();
    }

    public GenreCommonProto.GenreTranslationResponse toProtoGenreTranslation(GenreTranslationResponseDto genreTranslationResponseDto) {
        return GenreCommonProto.GenreTranslationResponse.newBuilder()
            .setId(genreTranslationResponseDto.id())
            .setGenreId(genreTranslationResponseDto.genreId())
            .setLanguageCode(genreTranslationResponseDto.languageCode())
            .setName(genreTranslationResponseDto.name())
            .setDescription(genreTranslationResponseDto.description())
            .build();
    }

    public GenreCommonProto.GenreResponse toProtoGenre(GenreResponseDto genreResponseDto) {
        return GenreCommonProto.GenreResponse.newBuilder()
            .setId(genreResponseDto.id())
            .setAlias(genreResponseDto.alias())
            .setSort(genreResponseDto.sort())
            .build();
    }

    public GenreCommonProto.GenreResponseList toProtoGenres(List<GenreResponseDto> genresResponseDto) {
        var genres = genresResponseDto.stream().map(this::toProtoGenre).toList();
        return GenreCommonProto.GenreResponseList.newBuilder()
            .addAllGenres(genres)
            .build();
    }

    public GenreRequestDto toGenreRequestDto(PrivateGenreProto.PrivateCreateRequest request) {
        return new GenreRequestDto(
            request.getAlias(),
            request.getSort()
        );
    }

    public List<GenreRequestDto> toGenresRequestDto(PrivateGenreProto.PrivateCreateBatchRequest requests) {
        return requests.getGenresList().stream().map(this::toGenreRequestDto).toList();
    }

    public GenreRequestDto toGenreRequestDto(PrivateGenreProto.PrivateUpdateRequest request) {
        return new GenreRequestDto(
            request.getAlias(),
            request.getSort()
        );
    }

    public GenreTranslationRequestDto toGenreTranslationRequestDto(PrivateGenreTranslationProto.PrivateTranslationCreateRequest request) {
        return new GenreTranslationRequestDto(
            request.getGenreId(),
            request.getLanguageCode(),
            request.getName(),
            request.getDescription()
        );
    }

    public GenreTranslationRequestDto toGenreTranslationRequestDto(PrivateGenreTranslationProto.PrivateTranslationUpdateRequest request) {
        return new GenreTranslationRequestDto(
            request.getGenreId(),
            request.getLanguageCode(),
            request.getName(),
            request.getDescription()
        );
    }
}
