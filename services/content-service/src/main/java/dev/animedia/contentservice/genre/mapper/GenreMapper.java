package dev.animedia.contentservice.genre.mapper;

import java.util.*;
import java.util.stream.Collectors;

import dev.animedia.contentservice.genre.dto.request.GenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;

@Component
public class GenreMapper {

    public Genre toGenre(GenreRequestDto genreRequestDto) {
        Genre genre = new Genre();
        genre.setAlias(genreRequestDto.alias());
        genre.setSort(genreRequestDto.sort());
        return genre;
    }

    public GenreResponseDto toGenreResponseDto(Genre genre) {
        return new GenreResponseDto(
            genre.getId(),
            genre.getAlias(),
            genre.getSort()
        );
    }

    public List<GenreResponseDto> toGenresResponseDto(List<Genre> genres) {
        return genres.stream().map(this::toGenreResponseDto).toList();
    }

    public GenreWithTranslationResponseDto toGenresWithTranslationResponseDto(GenreResponseDto genreResponseDto, GenreTranslationResponseDto genreTranslation) {
        return new GenreWithTranslationResponseDto(
            genreResponseDto.id(),
            genreResponseDto.alias(),
            genreResponseDto.sort(),
            genreTranslation.id(),
            genreTranslation.languageCode(),
            genreTranslation.name(),
            genreTranslation.description()
        );
    }

    public List<GenreWithTranslationResponseDto> toGenresWithTranslationResponseDto(List<GenreWithTranslationsResponseDto> genresWithTranslationsResponseDto) {
        return genresWithTranslationsResponseDto.stream().map(
            genreWithTranslationsResponseDto -> {
                if (genreWithTranslationsResponseDto.translations().isEmpty()) return null;
                GenreTranslationResponseDto genreTranslation = genreWithTranslationsResponseDto.translations().getFirst();
                return new GenreWithTranslationResponseDto(
                    genreWithTranslationsResponseDto.id(),
                    genreWithTranslationsResponseDto.alias(),
                    genreWithTranslationsResponseDto.sort(),
                    genreTranslation.id(), genreTranslation.languageCode(), genreTranslation.name(), genreTranslation.description()
                );
            }
        ).toList();
    }
    
    public List<GenreWithTranslationResponseDto> toGenresWithTranslationResponseDto(
        List<GenreResponseDto> genres,
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
            .filter(genre -> genreTranslationMap.containsKey(genre.id()))
            .map(genre -> this.toGenresWithTranslationResponseDto(
                genre,
                genreTranslationMap.get(genre.id())
            ))
            .toList();
    }

    public List<GenreWithTranslationsResponseDto> toGenresWithTranslationsResponseDto(
        List<GenreWithTranslationResponseDto> genresWithTranslationResponseDto
    ) {
        // GenreId, GenreTranslationsResponseDto
        Map<Long, List<GenreTranslationResponseDto>> genreTranslationsMap = new HashMap<>();
        // For genres without translations
        Map<Long, GenreResponseDto> genreResponseDtoMap = new HashMap<>();
        for (var genreWithTranslationResponseDto : genresWithTranslationResponseDto) {

            if (!genreResponseDtoMap.containsKey(genreWithTranslationResponseDto.id())) {
                GenreResponseDto genreResponseDto = new GenreResponseDto(
                    genreWithTranslationResponseDto.id(),
                    genreWithTranslationResponseDto.alias(),
                    genreWithTranslationResponseDto.sort()
                );
                genreResponseDtoMap.put(genreResponseDto.id(), genreResponseDto);
            }

            if (genreWithTranslationResponseDto.genreTranslationId() == null) continue;

            GenreTranslationResponseDto genreTranslationResponseDto = new GenreTranslationResponseDto(
                genreWithTranslationResponseDto.genreTranslationId(),
                genreWithTranslationResponseDto.id(),
                genreWithTranslationResponseDto.languageCode(),
                genreWithTranslationResponseDto.name(),
                genreWithTranslationResponseDto.description()
            );

            if (genreTranslationsMap.containsKey(genreWithTranslationResponseDto.id())) {
                genreTranslationsMap.get(genreWithTranslationResponseDto.id()).add(
                    genreTranslationResponseDto
                );
            } else {
                List<GenreTranslationResponseDto> genreTranslationsList = new ArrayList<>();
                genreTranslationsList.add(genreTranslationResponseDto);
                genreTranslationsMap.put(
                    genreWithTranslationResponseDto.id(),
                    genreTranslationsList
                );
            }
        }

        return genreResponseDtoMap.values().stream()
            .map(genreResponseDto -> new GenreWithTranslationsResponseDto(
                genreResponseDto.id(),
                genreResponseDto.alias(),
                genreResponseDto.sort(),
                genreTranslationsMap.get(genreResponseDto.id())
            ))
            .toList();
    }
}
