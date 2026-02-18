package dev.animedia.contentservice.app.dto;

import org.springframework.data.domain.Page;

import java.util.List;

public class PagedResponse<T> {
	private final List<T> content;
	private final PagedMetadata page;
	private final boolean first;
	private final boolean last;

	private PagedResponse(List<T> content, PagedMetadata page, boolean first, boolean last) {
		this.content = content;
		this.page = page;
		this.first = first;
		this.last = last;
	}

	public record PagedMetadata(
		int number,
		int numberOfElements,
		int size,
		long totalElements,
		int totalPages
	) {}


	public List<T> getContent() {
		return content;
	}

	public PagedMetadata getPage() {
		return page;
	}

	public boolean isFirst() {
		return first;
	}

	public boolean isLast() {
		return last;
	}

	public static <T> PagedResponse<T> getPagedResponse(Page<T> pageResponse) {
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
