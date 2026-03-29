package dev.animedia.languageservice.application.dto;

public record Pageable(
    long page,
    long size,
    String sortBy,
    Direction direction
) {
    public enum Direction {
        DESC,
        ASC
    }
}
