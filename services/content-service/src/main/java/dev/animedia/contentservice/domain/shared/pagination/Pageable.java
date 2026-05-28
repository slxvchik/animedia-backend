package dev.animedia.contentservice.domain.shared.pagination;

public record Pageable(
    int page,
    int size,
	String sortField,
	SortDirection sortDirection
) {
	public enum SortDirection {
		DESC,
		ASC
	}
}