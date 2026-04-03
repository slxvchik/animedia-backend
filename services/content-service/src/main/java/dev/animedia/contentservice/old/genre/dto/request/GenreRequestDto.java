package dev.animedia.contentservice.old.genre.dto.request;

import dev.animedia.contentservice.old.genre.GenreConstants;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record GenreRequestDto(
    @NotNull(message = GenreConstants.GENRE_ALIAS_REQUIRED_MESSAGE)
    @Pattern(regexp = GenreConstants.GENRE_ALIAS_PATTERN, message = GenreConstants.GENRE_ALIAS_INVALID_CHARS_MESSAGE)
    String alias,
    Long sort
) {}
