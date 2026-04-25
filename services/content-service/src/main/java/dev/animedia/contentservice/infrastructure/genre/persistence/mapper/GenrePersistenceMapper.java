package dev.animedia.contentservice.infrastructure.genre.persistence.mapper;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreTranslation;
import dev.animedia.contentservice.infrastructure.genre.persistence.dto.GenreTranslationRowDto;
import dev.animedia.contentservice.infrastructure.genre.persistence.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.genre.persistence.model.GenreTranslationEntity;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class GenrePersistenceMapper {
    public List<Genre> toGenreList(List<GenreTranslationRowDto> genreTranslationRowDtoList) {
        if (genreTranslationRowDtoList.isEmpty()) return List.of();

        Map<Long, List<GenreTranslationRowDto>> genreRowMap = genreTranslationRowDtoList.stream()
            .collect(Collectors.groupingBy(GenreTranslationRowDto::id));

        return genreRowMap.values().stream()
            .map(genreRowList -> {
                GenreTranslationRowDto first = genreRowList.getFirst();
                return new Genre(
                    first.id(),
                    first.alias(),
                    first.sortOrder(),
                    genreRowList.stream().map(row -> new GenreTranslation(
                        row.translationId(),
                        row.languageCode(),
                        row.name(),
                        row.description())
                    ).collect(Collectors.toSet())
                );
            })
            .sorted(Comparator.comparing(Genre::getSortOrder))
            .toList();
    }

    public Genre toGenre(GenreEntity genreEntity) {
        return new Genre(
            genreEntity.getId(),
            genreEntity.getAlias(),
            genreEntity.getSortOrder(),
            genreEntity.getTranslationSet().stream()
                .map(this::toGenreTranslation)
                .collect(Collectors.toSet())
        );
    }

    public GenreTranslation toGenreTranslation(GenreTranslationEntity genreTranslationEntity) {
        return new GenreTranslation(
            genreTranslationEntity.getId(),
            genreTranslationEntity.getLanguageCode(),
            genreTranslationEntity.getName(),
            genreTranslationEntity.getDescription()
        );
    }
}
