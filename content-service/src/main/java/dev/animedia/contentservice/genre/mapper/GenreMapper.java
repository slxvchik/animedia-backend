package dev.animedia.contentservice.genre.mapper;

import java.util.*;
import java.util.stream.Collectors;

import dev.animedia.contentservice.genre.dto.request.CreateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;

@Component
public class GenreMapper {

    public GenreWithTranslationResponseDto toGenreWithTranslationResponseDto(GenreResponseDto genreResponseDto, GenreTranslationResponseDto genreTranslation) {
        return new GenreWithTranslationResponseDto(
            genreResponseDto.id(),
            genreResponseDto.alias(),
            genreResponseDto.sort(),
            genreTranslation.id(),
            genreTranslation.name(),
            genreTranslation.description()
        );
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

    public Genre toGenre(CreateGenreRequestDto createGenreRequestDto) {

        Genre genre = new Genre();
        genre.setAlias(createGenreRequestDto.alias());
        genre.setSort(createGenreRequestDto.sort());

        return genre;
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
            .map(genre -> this.toGenreWithTranslationResponseDto(
                genre,
                genreTranslationMap.get(genre.id())
            ))
            .toList();
    }

	public Page<GenreResponseDto> toPageGenreResponseDto(Page<Genre> genres) {
        return genres.map(this::toGenreResponseDto);
	}

    public GenreWithTranslationsResponseDto toGenreWithTranslationsResponseDto(
        GenreResponseDto genreResponseDto,
        List<GenreTranslationResponseDto> genreTranslationsResponseDto
    ) {
        return new GenreWithTranslationsResponseDto(
            genreResponseDto.id(),
            genreResponseDto.alias(),
            genreResponseDto.sort(),
            genreTranslationsResponseDto
        );
    }

    public List<GenreWithTranslationsResponseDto> toGenresWithTranslationsResponseDto(
        List<GenreResponseDto> genresResponseDto,
        List<GenreTranslationResponseDto> genresTranslationsResponseDto
    ) {
        // GenreId, GenreTranslationResponseDto
        Map<Long, List<GenreTranslationResponseDto>> genreTranslationsMap = new HashMap<>();

        for (var genreTranslationResponseDto : genresTranslationsResponseDto) {
            if (genreTranslationsMap.containsKey(genreTranslationResponseDto.genreId())) {
                genreTranslationsMap.get(genreTranslationResponseDto.genreId()).add(genreTranslationResponseDto);
            } else {
                genreTranslationsMap.put(
                    genreTranslationResponseDto.genreId(),
                    List.of(genreTranslationResponseDto)
                );
            }
        }

        return genresResponseDto.stream()
                .map(genre -> this.toGenreWithTranslationsResponseDto(
                    genre,
                    genreTranslationsMap.get(genre.id())
                ))
                .toList();
    }
}
