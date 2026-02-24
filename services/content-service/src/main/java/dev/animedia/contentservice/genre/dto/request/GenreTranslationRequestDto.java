package dev.animedia.contentservice.genre.dto.request;

import dev.animedia.contentservice.app.exception.AppExceptionConstants;
import dev.animedia.contentservice.genre.GenreConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GenreTranslationRequestDto(
        @NotNull(message = GenreConstants.GENRE_ID_REQUIRED_MESSAGE)
        Long genreId,
        @NotBlank(message = AppExceptionConstants.LANGUAGE_CODE_REQUIRED_MESSAGE)
        String languageCode,
        @NotBlank(message = GenreConstants.GENRE_TRANSLATION_NAME_REQUIRED_MESSAGE)
        String name,
        String description
) {}
