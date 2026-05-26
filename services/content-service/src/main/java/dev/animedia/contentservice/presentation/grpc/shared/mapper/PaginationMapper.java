package dev.animedia.contentservice.presentation.grpc.shared.mapper;

import dev.animedia.contentservice.presentation.grpc.shared.exception.SortFieldNotAllowedException;
import dev.animedia.grpc.common.CommonProto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PaginationMapper {
	public <T> CommonProto.PaginationResponse toProtoPaginationResponse(Page<T> page) {
		return CommonProto.PaginationResponse.newBuilder()
			.setCurrentPage(page.getNumber())
			.setPageSize(page.getSize())
			.setTotalPages(page.getTotalPages())
			.setTotalElements(page.getTotalElements())
			.setHasNext(page.hasNext())
			.setHasPrevious(page.hasPrevious())
			.build();
	}

	public Pageable toPageable(CommonProto.PaginationRequest paginationRequest, Set<String> allowedFields) {
		if (paginationRequest.hasSort()) {
			return toPageableWithSort(paginationRequest, allowedFields);
		}
		return PageRequest.of(
			paginationRequest.getPage() >= 0 ? paginationRequest.getPage() : 0,
			paginationRequest.getSize() >= 1 ? paginationRequest.getSize() : 10
		);
	}

	private Pageable toPageableWithSort(CommonProto.PaginationRequest paginationRequest, Set<String> allowedFields) {
		var sort = toSpringSort(paginationRequest.getSort(), allowedFields);
		return PageRequest.of(
			paginationRequest.getPage() >= 0 ? paginationRequest.getPage() : 0,
			paginationRequest.getSize() >= 1 ? paginationRequest.getSize() : 10,
			sort
		);
	}

	private Sort toSpringSort(CommonProto.Sort sortProto, Set<String> allowedFields) {
		String property = sortProto.getField();

		if (property.isBlank() || !allowedFields.contains(property))
			throw new SortFieldNotAllowedException(property);

		Sort.Direction springDirection = sortProto.getDirection() == CommonProto.SortDirection.DESC
			? Sort.Direction.DESC
			: Sort.Direction.ASC;

		return Sort.by(springDirection, property);
	}
}
