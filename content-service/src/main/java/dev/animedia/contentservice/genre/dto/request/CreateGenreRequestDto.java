package dev.animedia.contentservice.genre.dto.request;

public record CreateGenreRequestDto(
    String alias,
    Long sort
) {}
