package dev.animedia.contentservice.domain.shared.model;

public record Pageable(
    int page,
    int size,
	String sortField,
	String sortDirection
) {}