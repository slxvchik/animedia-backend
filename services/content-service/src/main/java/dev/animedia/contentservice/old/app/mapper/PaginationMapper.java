package dev.animedia.contentservice.old.app.mapper;

import dev.animedia.grpc.common.CommonProto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

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

	public Pageable toPageable(CommonProto.PaginationRequest paginationRequest) {
		return PageRequest.of(
			paginationRequest.getPage() >= 0 ? paginationRequest.getPage() : 0,
			paginationRequest.getSize() >= 1 ? paginationRequest.getSize() : 10
		);
	}

	public Pageable toPageableWithSort(CommonProto.PaginationRequest paginationRequest, Sort sort) {
		return PageRequest.of(
			paginationRequest.getPage() >= 0 ? paginationRequest.getPage() : 0,
			paginationRequest.getSize() >= 1 ? paginationRequest.getSize() : 10,
			sort
		);
	}
}
