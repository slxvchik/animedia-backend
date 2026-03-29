package dev.animedia.languageservice.infrastructure.grpc.mapper;

import com.google.protobuf.Any;
import dev.animedia.grpc.common.CommonProto;
import dev.animedia.languageservice.application.dto.Page;
import dev.animedia.languageservice.application.dto.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class PaginationGrpcMapper {

	public <T, P extends com.google.protobuf.Message> CommonProto.PaginationResponse toProtoPaginationResponse(Page<T> page, Function<T, P> converter) {
		List<Any> anyContent = page.content().stream()
			.map(converter)
			.map(Any::pack)
			.toList();
		return CommonProto.PaginationResponse.newBuilder()
			.addAllContent(anyContent)
			.setCurrentPage(page.pageNumber())
			.setPageSize(page.pageSize())
			.setTotalPages(page.totalPages())
			.setTotalElements(page.totalElements())
			.setHasNext(page.hasNext())
			.setHasPrevious(page.hasPrevious())
			.build();
	}

	public Pageable toPageable(CommonProto.PaginationRequest paginationRequest) {
		return new Pageable(
			paginationRequest.getPage() >= 0 ? paginationRequest.getPage() : 0,
			paginationRequest.getSize() >= 1 ? paginationRequest.getSize() : 10,
			paginationRequest.getSort().getField(),
			toDirection(paginationRequest.getSort().getDirection())
		);
	}

	private Pageable.Direction toDirection(CommonProto.SortDirection direction) {
		if (direction.getNumber() == CommonProto.SortDirection.ASC_VALUE) {
			return Pageable.Direction.ASC;
		}
		return Pageable.Direction.DESC;
	}
}
