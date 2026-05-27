package dev.animedia.contentservice.infrastructure.persistence.genre.mapper;

import dev.animedia.contentservice.domain.genre.model.Genre;
import dev.animedia.contentservice.domain.genre.model.GenreTranslation;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreEntity;
import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreTranslationEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GenrePersistenceMapper {
    public Genre toGenre(GenreEntity genreEntity) {
        if (genreEntity == null) return null;
        return new Genre(
            genreEntity.getId(),
            genreEntity.getAlias(),
            genreEntity.getSortOrder(),
            genreEntity.getActive(),
            genreEntity.getTranslations().stream()
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

        ge.setTranslations(
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
