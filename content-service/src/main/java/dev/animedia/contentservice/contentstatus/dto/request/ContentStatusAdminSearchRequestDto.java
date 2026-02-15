package dev.animedia.contentservice.contentstatus.dto.request;

import org.springframework.data.domain.Pageable;

import java.util.List;

public record ContentStatusAdminSearchRequestDto(
	List<Long> contentStatusIds,
	List<String> languageCodes,
	List<String> aliases,
	List<String> names,
	Pageable pageable
) {
}
