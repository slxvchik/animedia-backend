package dev.animedia.languageservice.domain.model;

public record Pageable(
    long page,
    long size
) {}