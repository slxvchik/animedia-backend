package dev.animedia.contentservice.app.mapper;

import dev.animedia.contentservice.app.dto.PagedResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class PageMapper {
	public <T> PagedResponse<T> toPagedResponse(Page<T> pageResponse) {
		return new PagedResponse<>(
			pageResponse.getContent(),
			new PagedResponse.PagedMetadata(
				pageResponse.getNumber(),
				pageResponse.getNumberOfElements(),
				pageResponse.getSize(),
				pageResponse.getTotalElements(),
				pageResponse.getTotalPages()
			),
			pageResponse.isFirst(),
			pageResponse.isLast()
		);
	}
}
