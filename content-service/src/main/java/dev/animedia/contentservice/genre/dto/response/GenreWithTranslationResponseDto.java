package dev.animedia.contentservice.genre.dto.response;

public record GenreWithTranslationResponseDto(
    Long id,
    String alias,
    Long sort,
    Long genreTranslationId,
    String name,
    String description
) {
}
    
