package dev.animedia.contentservice.old.genre.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import dev.animedia.contentservice.old.genre.dto.request.GenreTranslationRequestDto;
import dev.animedia.contentservice.old.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.old.genre.model.Genre;
import dev.animedia.contentservice.old.genre.model.GenreTranslation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class GenreTranslationMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public GenreTranslation toGenreTranslation(GenreTranslationRequestDto requestDto) {

        Genre genreRef = entityManager.getReference(Genre.class, requestDto.genreId());

        GenreTranslation genreTranslation = new GenreTranslation();
        genreTranslation.setGenre(genreRef);
        genreTranslation.setLanguageCode(requestDto.languageCode());
        genreTranslation.setName(requestDto.name());
        genreTranslation.setDescription(requestDto.description());

        return genreTranslation;
    }

    public GenreTranslationResponseDto toGenreTranslationResponseDto(GenreTranslation genreTranslation) {
        return new GenreTranslationResponseDto(
            genreTranslation.getId(),
            genreTranslation.getGenre().getId(),
            genreTranslation.getLanguageCode(),
            genreTranslation.getName(),
            genreTranslation.getDescription()
        );
    }

    public List<GenreTranslationResponseDto> toGenreTranslationsResponseDto(List<GenreTranslation> genreTranslation) {
        return genreTranslation.stream()
            .map(this::toGenreTranslationResponseDto)
            .toList();
    }
}
