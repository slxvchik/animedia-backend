package dev.animedia.contentservice.genre.core;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import dev.animedia.contentservice.genre.core.dto.GenreResponseDto;
import dev.animedia.contentservice.genre.translation.GenreTranslation;

public class GenreMapper {
    public static GenreResponseDto toGenreResponseDto(Genre genre, GenreTranslation genreTranslation) {
        return new GenreResponseDto(
            genre.getId(),
            genre.getAlias(),
            genre.getSort(),
            genreTranslation.getId(),
            genreTranslation.getName(),
            genreTranslation.getDescription()
        );
    }
    
    public static List<GenreResponseDto> toListGenreResponseDto(
        List<Genre> genres,
        List<GenreTranslation> genreTranslations
    ) {
        // GenreId, GenreTranslation
        Map<Long, GenreTranslation> genreTranslationsMap = genreTranslations
            .stream()
            .collect(
                Collectors.toMap(
                    gt -> gt.getGenre().getId(),
                    gt -> gt
                )
            );
        
        genres.forEach(genre -> {});
        return null;
    }
}
