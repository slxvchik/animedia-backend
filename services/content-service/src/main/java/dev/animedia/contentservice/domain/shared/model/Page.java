package dev.animedia.contentservice.domain.shared.model;

import java.util.List;
import java.util.function.Function;

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
) {
    public <K> Page<K> changeContent(Function<T, K> mapper) {
        List<K> newContent = content.stream().map(mapper).toList();
        return changeContent(newContent);
    }

    public <K> Page<K> changeContent(List<K> newContent) {
        return new Page<>(
            newContent,
            totalElements,
            totalPages,
            pageNumber,
            pageSize,
            isFirst,
            isLast,
            hasNext,
            hasPrevious
        );
    }
}