package dev.animedia.contentservice.infrastructure.persistence.genre.mapper;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreTranslation;
import dev.animedia.contentservice.infrastructure.persistence.genre.dto.GenreTranslationRowDto;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreTranslationEntity;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class GenrePersistenceMapper {
    public List<Genre> toGenreList(List<GenreTranslationRowDto> genreTranslationRowDtoList) {
        if (genreTranslationRowDtoList == null || genreTranslationRowDtoList.isEmpty()) return List.of();

        Map<Long, List<GenreTranslationRowDto>> genreRowMap = genreTranslationRowDtoList.stream()
            .collect(Collectors.groupingBy(GenreTranslationRowDto::id));

        return genreRowMap.values().stream()
            .map(genreRowList -> {
                GenreTranslationRowDto first = genreRowList.getFirst();

                Set<GenreTranslation> translationSet = genreRowList.stream().map(row -> new GenreTranslation(
                    row.translationId(),
                    row.languageCode(),
                    row.name(),
                    row.description())
                ).collect(Collectors.toSet());

                return new Genre(
                    first.id(),
                    first.alias(),
                    first.sortOrder(),
                    first.active(),
                    translationSet
                );
            })
            .sorted(Comparator.comparing(Genre::getSortOrder))
            .toList();
    }

    public Genre toGenre(GenreEntity genreEntity) {
        if (genreEntity == null) return null;
        return new Genre(
            genreEntity.getId(),
            genreEntity.getAlias(),
            genreEntity.getSortOrder(),
            genreEntity.getActive(),
            genreEntity.getTranslationSet().stream()
                .map(this::toGenreTranslation)
                .collect(Collectors.toSet())
        );
    }

    public GenreTranslation toGenreTranslation(GenreTranslationEntity genreTranslationEntity) {
        if (genreTranslationEntity == null) return null;
        return new GenreTranslation(
            genreTranslationEntity.getId(),
            genreTranslationEntity.getLanguageCode(),
            genreTranslationEntity.getName(),
            genreTranslationEntity.getDescription()
        );
    }

    public GenreEntity toGenreEntity(Genre genre) {
        if (genre == null) return null;

        GenreEntity ge = new GenreEntity();

        ge.setId(genre.getId());
        ge.setAlias(genre.getAlias());
        ge.setSortOrder(genre.getSortOrder());

        ge.setTranslationSet(
            genre.getTranslationSet().stream()
                .map(gt -> toGenreTranslationEntity(gt, ge))
                .collect(Collectors.toSet())
        );

        return ge;
    }

    public GenreTranslationEntity toGenreTranslationEntity(GenreTranslation genreTranslation, GenreEntity genreEntity) {
        if (genreTranslation == null) return null;

        GenreTranslationEntity gte = new GenreTranslationEntity();

        gte.setId(genreTranslation.getId());
        gte.setGenreEntity(genreEntity);
        gte.setLanguageCode(genreTranslation.getLanguageCode());
        gte.setName(genreTranslation.getName());
        gte.setDescription(genreTranslation.getDescription());

        return gte;
    }
}
