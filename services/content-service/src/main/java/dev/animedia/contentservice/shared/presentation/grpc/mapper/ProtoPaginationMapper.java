package dev.animedia.contentservice.shared.presentation.grpc.mapper;

import dev.animedia.contentservice.shared.domain.pagination.Page;
import dev.animedia.contentservice.shared.domain.pagination.Pageable;
import dev.animedia.contentservice.shared.presentation.grpc.exception.SortFieldNotAllowedException;
import dev.animedia.grpc.common.CommonProto;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProtoPaginationMapper {
	public <T> CommonProto.PaginationResponse toProtoPaginationResponse(Page<T> page) {
		return CommonProto.PaginationResponse.newBuilder()
			.setCurrentPage(page.pageNumber())
			.setPageSize(page.pageSize())
			.setTotalPages(page.totalPages())
			.setTotalElements(page.totalElements())
			.setHasNext(page.hasNext())
			.setHasPrevious(page.hasPrevious())
			.build();
	}

	public Pageable toDomainPageable(
		CommonProto.PaginationRequest paginationRequest
	) {
		int page = paginationRequest.getPage() >= 0 ? paginationRequest.getPage() : 0;
		int size = paginationRequest.getSize() >= 1 ? paginationRequest.getSize() : 10;
		return Pageable.of(page, size);
	}

	public Pageable toDomainPageable(
		CommonProto.PaginationRequest paginationRequest,
		Set<String> allowedFields
	) {
		int page = paginationRequest.getPage() >= 0 ? paginationRequest.getPage() : 0;
		int size = paginationRequest.getSize() >= 1 ? paginationRequest.getSize() : 10;

		if (!paginationRequest.hasSort()) {
			return Pageable.of(page, size, null, null);
		}

		CommonProto.Sort sortRequest = paginationRequest.getSort();
		String field = sortRequest.getField();

		if (field.isBlank() || !allowedFields.contains(field)) {
			throw new SortFieldNotAllowedException(field);
		}

		Pageable.SortDirection direction = (sortRequest.getDirection() == CommonProto.SortDirection.DESC)
			? Pageable.SortDirection.DESC
			: Pageable.SortDirection.ASC;

		return Pageable.of(page, size, field, direction);
	}
}
