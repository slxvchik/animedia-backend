package dev.animedia.contentservice.genre.dto.request;

import dev.animedia.contentservice.genre.GenreConstants;
import dev.animedia.contentservice.language.LanguageConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateGenreTranslationRequestDto(
        @NotNull(message = GenreConstants.GENRE_ID_REQUIRED_MESSAGE)
        Long genreId,
        @NotBlank(message = LanguageConstants.LANGUAGE_CODE_REQUIRED_MESSAGE)
        String languageCode,
        @NotBlank(message = GenreConstants.GENRE_NAME_REQUIRED_MESSAGE)
        String name,
        String description
) {}
