package dev.animedia.contentservice.app.dto;

import java.util.List;

public record PagedResponse<T>(
	List<T> content,
	PagedMetadata page,
	boolean first,
	boolean last
) {
	public record PagedMetadata(
		int number,
		int numberOfElements,
		int size,
		long totalElements,
		int totalPages
	) {}
}
