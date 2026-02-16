package dev.animedia.contentservice.contentstatus.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public record SearchContentStatusUserRequestDto(
	@NotBlank
	@NotNull
	String languageCode,
	@RequestParam(required = false)
	List<String> aliases,
	@RequestParam(required = false)
	List<String> names
) {
}
