package dev.animedia.contentservice.contentstatus.dto.request;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public record SearchContentStatusAdminRequestDto(
	@RequestParam(required = false)
	List<Long> contentStatusIds,
	@RequestParam(required = false)
	List<String> languageCodes,
	@RequestParam(required = false)
	List<String> aliases,
	@RequestParam(required = false)
	List<String> names,
	@PageableDefault
	Pageable pageable
) {
}
