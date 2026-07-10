package dev.animedia.contentservice.domain.shared.pagination;

public class Pageable {
	private int page;
	private int size;
	private String sortField;
	private SortDirection sortDirection;

	public enum SortDirection {
		DESC,
		ASC
	}

	private Pageable(int page, int size, String sortField, SortDirection sortDirection) {
		this.page = page;
		this.size = size;
		this.sortField = sortField;
		this.sortDirection = sortDirection;
	}

	public static Pageable of(int page, int size) {
		return new Pageable(page, size, null, null);
	}

	public static Pageable of(int page, int size, String sortField, SortDirection sortDirection) {
		return new Pageable(page, size, sortField, sortDirection);
	}
}