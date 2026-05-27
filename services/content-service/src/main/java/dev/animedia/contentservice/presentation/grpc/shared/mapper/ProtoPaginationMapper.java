package dev.animedia.contentservice.presentation.grpc.shared.mapper;

import dev.animedia.contentservice.domain.shared.model.Page;
import dev.animedia.contentservice.domain.shared.model.Pageable;
import dev.animedia.contentservice.presentation.grpc.shared.exception.SortFieldNotAllowedException;
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

	public Pageable toDomainPageable(CommonProto.PaginationRequest paginationRequest, Set<String> allowedFields) {
		int page = paginationRequest.getPage() >= 0 ? paginationRequest.getPage() : 0;
		int size = paginationRequest.getSize() >= 1 ? paginationRequest.getSize() : 10;

		if (!paginationRequest.hasSort()) {
			return new Pageable(page, size, null, null);
		}

		CommonProto.Sort sortRequest = paginationRequest.getSort();
		String field = sortRequest.getField();

		if (field.isBlank() || !allowedFields.contains(field)) {
			throw new SortFieldNotAllowedException(field);
		}

		Pageable.SortDirection direction = (sortRequest.getDirection() == CommonProto.SortDirection.DESC)
			? Pageable.SortDirection.DESC
			: Pageable.SortDirection.ASC;

		return new Pageable(page, size, field, direction);
	}
}
