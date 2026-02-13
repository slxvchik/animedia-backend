package dev.animedia.contentservice.genre.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import dev.animedia.contentservice.genre.dto.request.GenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.model.GenreTranslation;
import dev.animedia.contentservice.language.Language;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Component
public class GenreTranslationMapper {

    @PersistenceContext
    private EntityManager entityManager;

    public GenreTranslation toGenreTranslation(GenreTranslationRequestDto requestDto) {

        Genre genreRef = entityManager.getReference(Genre.class, requestDto.genreId());
        Language languageRef = entityManager.getReference(Language.class, requestDto.languageCode());

        GenreTranslation genreTranslation = new GenreTranslation();
        genreTranslation.setGenre(genreRef);
        genreTranslation.setLanguage(languageRef);
        genreTranslation.setName(requestDto.name());
        genreTranslation.setDescription(requestDto.description());

        return genreTranslation;
    }

    public GenreTranslationResponseDto toGenreTranslationResponseDto(GenreTranslation genreTranslation) {
        return new GenreTranslationResponseDto(
            genreTranslation.getId(),
            genreTranslation.getGenre().getId(),
            genreTranslation.getLanguage().getCode(),
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
