package dev.animedia.contentservice.infrastructure.persistence.shared.mapper;

import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationPersistenceMapper {
	public org.springframework.data.domain.Pageable toPageable(int page, int size) {
		return PageRequest.of(page, size);
	}

	public <T> Page<T> toDomainPage(org.springframework.data.domain.Page<T> springPage) {
		return new Page<>(
			springPage.getContent(),
			springPage.getTotalElements(),
			springPage.getTotalPages(),
			springPage.getNumber(),
			springPage.getSize(),
			springPage.isFirst(),
			springPage.isLast(),
			springPage.hasNext(),
			springPage.hasPrevious()
		);
	}
}
