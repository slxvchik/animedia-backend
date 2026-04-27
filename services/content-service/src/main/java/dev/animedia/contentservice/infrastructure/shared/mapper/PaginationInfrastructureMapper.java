package dev.animedia.contentservice.infrastructure.shared.mapper;

import dev.animedia.contentservice.domain.shared.model.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
public class PaginationInfrastructureMapper {
	public org.springframework.data.domain.Pageable toPageable(int page, int size, String sortField, String direction) {
		Sort sort = Sort.by(Sort.Direction.fromString(direction), sortField);
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
