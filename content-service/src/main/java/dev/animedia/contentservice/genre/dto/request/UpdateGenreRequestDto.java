package dev.animedia.contentservice.genre.dto.request;

public record UpdateGenreRequestDto (
    Long id,
    String alias,
    Long sort
) {}
