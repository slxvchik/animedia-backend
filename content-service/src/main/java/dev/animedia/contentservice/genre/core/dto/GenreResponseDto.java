package dev.animedia.contentservice.genre.core.dto;

public record GenreResponseDto(
    Long id,
    String alias,
    Long sort,
    Long genreTranslationId,
    String name,
    String description
) {
}
    
