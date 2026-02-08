package dev.animedia.contentservice.language;

import dev.animedia.contentservice.app.dto.AppResponseDto;
import dev.animedia.contentservice.app.dto.ContentResponse;
import dev.animedia.contentservice.app.dto.PagedResponse;
import dev.animedia.contentservice.app.exception.AppExceptionConstants;
import dev.animedia.contentservice.language.dto.LanguageRequestDto;
import dev.animedia.contentservice.language.dto.LanguageResponseDto;
import dev.animedia.contentservice.language.service.LanguageCommandService;
import dev.animedia.contentservice.language.service.LanguagePageService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/v1/language")
public class LanguageAdminController {

	private final LanguagePageService languagePageService;
	private final LanguageCommandService languageCommandService;

	@Autowired
	public LanguageAdminController(LanguagePageService languagePageService,
		LanguageCommandService languageCommandService
	) {
		this.languagePageService = languagePageService;
		this.languageCommandService = languageCommandService;
	}

	@GetMapping("/search")
	public ResponseEntity<AppResponseDto<PagedResponse<LanguageResponseDto>>> search(
		@PageableDefault
		Pageable pageable,
		@RequestParam(required = false)
		@Size(max = 100, message = AppExceptionConstants.BATCH_SIZE_LIMIT_MESSAGE)
		List<String> languagesCodes,
		@RequestParam(required = false)
		@Size(max = 100, message = AppExceptionConstants.BATCH_SIZE_LIMIT_MESSAGE)
		List<String> names
	) {
		Page<LanguageResponseDto> languagesResponseDto = languagePageService.search(languagesCodes, names, pageable);
		return ResponseEntity.ok(
			AppResponseDto.success(
				PagedResponse.getPagedResponse(languagesResponseDto)
			)
		);
	}

	@PostMapping
	public ResponseEntity<AppResponseDto<ContentResponse<LanguageResponseDto>>> create(
		@Validated
		LanguageRequestDto languageRequestDto
	) {
		var createdGenre = languageCommandService.create(languageRequestDto);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content(createdGenre)
			)
		);
	}

	@PutMapping
	public ResponseEntity<AppResponseDto<ContentResponse<LanguageResponseDto>>> update(
		@Validated
		LanguageRequestDto languageRequestDto
	) {
		var updatedGenre = languageCommandService.update(languageRequestDto);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content(updatedGenre)
			)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AppResponseDto<ContentResponse<LanguageResponseDto>>> delete(
		@PathVariable
		String id
	) {
		languageCommandService.delete(id);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content()
			)
		);
	}
}
