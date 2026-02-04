package dev.animedia.contentservice.genre.dto.request;

import dev.animedia.contentservice.genre.GenreConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateGenreTranslationRequestDto(
        @NotNull(message = GenreConstants.GENRE_TRANSLATION_ID_REQUIRED_MESSAGE)
        Long id,
        @NotBlank(message = GenreConstants.GENRE_TRANSLATION_NAME_REQUIRED_MESSAGE)
        String name,
        String description
) {}
