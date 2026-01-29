package dev.animedia.contentservice.genre.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.model.GenreTranslation;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.exception.GenreTranslationIdNotFoundException;

@Component
public class GenreMapper {

    public GenreMapper() {}

    public GenreWithTranslationResponseDto toGenreResponseDto(Genre genre, GenreTranslation genreTranslation) {
        return new GenreWithTranslationResponseDto(
            genre.getId(),
            genre.getAlias(),
            genre.getSort(),
            genreTranslation.getId(),
            genreTranslation.getName(),
            genreTranslation.getDescription()
        );
    }

    public GenreResponseDto toGenreResponseDto(Genre genre) {
        return null;
    }
    
    public List<GenreWithTranslationResponseDto> toGenresWithTranslationsResponseDto(
        List<Genre> genres,
        List<GenreTranslation> genreTranslations
    ) {

        List<GenreWithTranslationResponseDto> genreResponseDtoList = new ArrayList<>();

        // GenreId, GenreTranslation
        Map<Long, GenreTranslation> genreTranslationMap = genreTranslations.stream()
            .collect(
                Collectors.toMap(
                    genreTranslation -> genreTranslation.getGenre().getId(),
                    genreTranslation -> genreTranslation
                )
            );
        
        genres.forEach(genre -> {

            var genreTranslation = Optional.of(genreTranslationMap)
                .map(gtMap -> gtMap.get(genre.getId()))
                .orElseThrow(() -> new GenreTranslationIdNotFoundException());

            var genreResponseDto = this.toGenreResponseDto(
                genre,
                genreTranslation
            );

            genreResponseDtoList.add(genreResponseDto);
        });
        return genreResponseDtoList;
    }
}
