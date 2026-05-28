package dev.animedia.contentservice.infrastructure.persistence.shared.mapper;

import dev.animedia.contentservice.domain.shared.pagination.Page;
import dev.animedia.contentservice.domain.shared.pagination.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationPersistenceMapper {
	public org.springframework.data.domain.Pageable toPageable(int page, int size, String sortField, Pageable.SortDirection sortDirection) {
		if (sortField == null || sortField.isBlank()) {
			return toPageable(page, size);
		}

		Sort.Direction direction = (sortDirection == null || sortDirection == Pageable.SortDirection.DESC)
			? Sort.Direction.DESC
			: Sort.Direction.ASC;

		Sort sort = Sort.by(direction, sortField);

		return PageRequest.of(page, size, sort);
	}

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
