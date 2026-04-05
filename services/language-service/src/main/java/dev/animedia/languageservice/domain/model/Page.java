package dev.animedia.languageservice.domain.model;

import java.util.List;

public record Page<T>(
    List<T> content,
    long totalElements,
    long totalPages,
    long pageNumber,
    long pageSize,
    boolean isFirst,
    boolean isLast,
    boolean hasNext,
    boolean hasPrevious
) {}
