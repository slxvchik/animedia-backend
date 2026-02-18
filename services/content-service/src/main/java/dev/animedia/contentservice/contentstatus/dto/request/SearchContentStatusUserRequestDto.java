package dev.animedia.contentservice.contentstatus.dto.request;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public record SearchContentStatusUserRequestDto(
	@RequestParam(required = false)
	List<String> aliases,
	@RequestParam(required = false)
	List<String> names
) {
}
