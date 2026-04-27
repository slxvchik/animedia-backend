package dev.animedia.contentservice.application.shared.mapper;

import dev.animedia.contentservice.domain.shared.model.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class PaginationApplicationMapper {
    public <T, K> Page<K> changeContent(Page<T> page, Function<T, K> contentMapper) {
        List<K> newContent = page.content()
            .stream()
            .map(contentMapper)
            .toList();

        return new Page<>(
            newContent,
            page.totalElements(),
            page.totalPages(),
            page.pageNumber(),
            page.pageSize(),
            page.isFirst(),
            page.isLast(),
            page.hasNext(),
            page.hasPrevious()
        );
    }

    public <T, K> Page<K> changeContent(Page<T> page, List<K> newContent) {
        return new Page<>(
            newContent,
            page.totalElements(),
            page.totalPages(),
            page.pageNumber(),
            page.pageSize(),
            page.isFirst(),
            page.isLast(),
            page.hasNext(),
            page.hasPrevious()
        );
    }
}
