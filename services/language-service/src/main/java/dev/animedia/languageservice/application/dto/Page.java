package dev.animedia.languageservice.application.dto;

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
