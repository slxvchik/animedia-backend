package dev.animedia.contentservice.genre.mapper;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import dev.animedia.contentservice.genre.dto.request.CreateGenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreTranslationRequestDto;
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

    public GenreTranslationResponseDto toGenreTranslationResponseDto(GenreTranslation genreTranslation) {
        return new GenreTranslationResponseDto(
            genreTranslation.getId(),
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

    public GenreTranslation toGenreTranslation(CreateGenreTranslationRequestDto requestDto) {

        Genre genreRef = entityManager.getReference(Genre.class, requestDto.genreId());
        Language languageRef = entityManager.getReference(Language.class, requestDto.languageCode());
        
        GenreTranslation genreTranslation = new GenreTranslation();
        genreTranslation.setGenre(genreRef);
        genreTranslation.setLanguage(languageRef);
        genreTranslation.setName(requestDto.name());
        genreTranslation.setDescription(requestDto.description());

        return genreTranslation;
    }

    public GenreTranslation toGenreTranslation(UpdateGenreTranslationRequestDto requestDto) {

        GenreTranslation genreTranslation = new GenreTranslation();
        genreTranslation.setId(requestDto.id());
        genreTranslation.setName(requestDto.name());
        genreTranslation.setDescription(requestDto.description());

        return genreTranslation;
    }

    public List<GenreTranslation> toGenreTranslationsFromCreate(List<CreateGenreTranslationRequestDto> requestsDto) {
        return toGenreTranslations(requestsDto, this::toGenreTranslation);
    }

    public List<GenreTranslation> toGenreTranslationsFromUpdate(List<UpdateGenreTranslationRequestDto> requestsDto) {
        return toGenreTranslations(requestsDto, this::toGenreTranslation);
    }

    private <T> List<GenreTranslation> toGenreTranslations(
        List<T> requestsDto,
        Function<T, GenreTranslation> mapper
    ) {
        return requestsDto.stream().map(mapper)
            .filter(Objects::nonNull)
            .toList();
    }
}
