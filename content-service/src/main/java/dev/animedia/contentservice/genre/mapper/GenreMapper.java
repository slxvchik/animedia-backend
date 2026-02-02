package dev.animedia.contentservice.genre.mapper;

import java.util.*;
import java.util.stream.Collectors;

import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import org.springframework.stereotype.Component;

import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.model.GenreTranslation;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.exception.GenreTranslationNotFoundException;

@Component
public class GenreMapper {

    public GenreWithTranslationResponseDto toGenreWithTranslationResponseDto(Genre genre, GenreTranslationResponseDto genreTranslation) {
        return new GenreWithTranslationResponseDto(
            genre.getId(),
            genre.getAlias(),
            genre.getSort(),
            genreTranslation.id(),
            genreTranslation.name(),
            genreTranslation.description()
        );
    }

    public GenreWithTranslationsResponseDto toGenreWithTranslationsResponseDto(Genre genre, List<GenreTranslationResponseDto> genreTranslations) {
        return new GenreWithTranslationsResponseDto(
            genre.getId(),
            genre.getAlias(),
            genre.getSort(),
            genreTranslations
        );
    }

    public GenreResponseDto toGenreResponseDto(Genre genre) {
        return null;
    }
    
    public List<GenreWithTranslationResponseDto> toGenresWithTranslationResponseDto(
        List<Genre> genres,
        List<GenreTranslationResponseDto> genreTranslations
    ) {

        // GenreId, GenreTranslationResponseDto
        Map<Long, GenreTranslationResponseDto> genreTranslationMap = genreTranslations.stream()
            .collect(
                Collectors.toMap(
                    GenreTranslationResponseDto::genreId,
                    gt -> gt,
                    (first, second) -> first
                )
            );

        return genres.stream()
            .filter(genre -> genreTranslationMap.containsKey(genre.getId()))
            .map(genre -> this.toGenreWithTranslationResponseDto(
                genre,
                genreTranslationMap.get(genre.getId())
            ))
            .toList();
    }

    public List<GenreWithTranslationsResponseDto> toGenresWithTranslationsResponseDto(
        List<Genre> genres,
        List<GenreTranslationResponseDto> genreTranslations
    ) {

        Map<Long, List<GenreTranslationResponseDto>> genreTranslationsMap = genreTranslations.stream()
            .collect(
                Collectors.groupingBy(
                   GenreTranslationResponseDto::genreId
                )
            );

        return genres.stream()
            .filter(genre -> genreTranslationsMap.containsKey(genre.getId()))
            .map(genre -> this.toGenreWithTranslationsResponseDto(
                genre,
                genreTranslationsMap.get(genre.getId())
            ))
            .toList();
    }
}
