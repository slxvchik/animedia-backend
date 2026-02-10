package dev.animedia.contentservice.contentstatus.dto.request;

import java.util.List;

public record ContentStatusSearchRequestDto(
	List<String> languageCodes,
	List<String> aliases,
	List<String> names
) {
}
