package dev.animedia.contentservice.domain.shared.model;

public record Pageable(
    long page,
    long size
) {}