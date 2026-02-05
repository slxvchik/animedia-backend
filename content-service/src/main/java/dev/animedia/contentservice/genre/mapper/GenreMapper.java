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
            genreTranslation.languageCode(),
            genreTranslation.name(),
            genreTranslation.description()
        );
    }

    public List<GenreWithTranslationResponseDto> toGenreWithTranslationResponseDto(List<GenreWithTranslationsResponseDto> genresWithTranslationsResponseDto) {
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
                List<GenreTranslationResponseDto> genreTranslationsList = new ArrayList<>();
                genreTranslationsList.add(genreTranslationResponseDto);
                genreTranslationsMap.put(
                    genreTranslationResponseDto.genreId(),
                    genreTranslationsList
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

    public List<GenreWithTranslationsResponseDto> toGenresWithTranslationsResponseDto(
        List<GenreWithTranslationResponseDto> genresWithTranslationResponseDto
    ) {
        // GenreId, GenreTranslationResponseDto
        Map<Long, List<GenreTranslationResponseDto>> genreTranslationsMap = new HashMap<>();
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
