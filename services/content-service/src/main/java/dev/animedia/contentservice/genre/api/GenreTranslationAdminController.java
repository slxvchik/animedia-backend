package dev.animedia.contentservice.genre.api;

import dev.animedia.contentservice.app.dto.AppResponseDto;
import dev.animedia.contentservice.app.dto.ContentResponse;
import dev.animedia.contentservice.app.dto.PagedResponse;
import dev.animedia.contentservice.app.exception.AppExceptionConstants;
import dev.animedia.contentservice.genre.GenreConstants;
import dev.animedia.contentservice.genre.dto.request.GenreTranslationRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.service.GenreTranslationCommandService;
import dev.animedia.contentservice.genre.service.GenreTranslationPageService;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/genre/translations")
public class GenreTranslationAdminController {

	private final GenreTranslationCommandService genreTranslationCommandService;
	private final GenreTranslationPageService genreTranslationPageService;

	@Autowired
	public GenreTranslationAdminController(
		GenreTranslationCommandService genreTranslationCommandService,
		GenreTranslationPageService genreTranslationPageService
	) {
		this.genreTranslationCommandService = genreTranslationCommandService;
		this.genreTranslationPageService = genreTranslationPageService;
	}

	@GetMapping("/search")
	public ResponseEntity<AppResponseDto<PagedResponse<GenreTranslationResponseDto>>> search(
		@PageableDefault
		Pageable pageable,
		@RequestParam(required = false)
		String name,
		@RequestParam(required = false)
		Long genreId,
		@RequestParam(required = false)
		@Size(max = 10, message = GenreConstants.GENRE_TRANSLATIONS_LANGUAGE_CODES_SIZE_LIMIT_MESSAGE)
		List<String> languageCodes
	) {
		Page<GenreTranslationResponseDto> genresTranslations = genreTranslationPageService.search(name, genreId, languageCodes, pageable);
		return ResponseEntity.ok(
			AppResponseDto.success(
				PagedResponse.getPagedResponse(genresTranslations)
			)
		);
	}

	@PostMapping
	public ResponseEntity<AppResponseDto<ContentResponse<GenreTranslationResponseDto>>> create(
		@RequestBody
		@Validated
		GenreTranslationRequestDto genreTranslationRequestDto
	) {
		var createdGenreTranslation = genreTranslationCommandService.create(genreTranslationRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(
			AppResponseDto.success(
				ContentResponse.content(createdGenreTranslation)
			)
		);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AppResponseDto<ContentResponse<GenreTranslationResponseDto>>> update(
		@PathVariable
		Long id,
		@RequestBody
		@Validated
		GenreTranslationRequestDto genreTranslationRequestDto
	) {
		var updatedGenreTranslation = genreTranslationCommandService.update(id, genreTranslationRequestDto);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content(updatedGenreTranslation)
			)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AppResponseDto<ContentResponse<GenreTranslationResponseDto>>> delete(
		@PathVariable
		@NotNull(message = GenreConstants.GENRE_TRANSLATION_ID_REQUIRED_MESSAGE)
		Long id
	) {
		genreTranslationCommandService.delete(id);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content()
			)
		);
	}

	@DeleteMapping("/batch")
	public ResponseEntity<AppResponseDto<ContentResponse<GenreTranslationResponseDto>>> batchDelete(
		@RequestParam
		@NotNull(message = GenreConstants.GENRE_TRANSLATION_ID_REQUIRED_MESSAGE)
		@Size(max = 100, message = AppExceptionConstants.BATCH_SIZE_LIMIT_MESSAGE)
		List<Long> ids
	) {
		genreTranslationCommandService.delete(ids);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content()
			)
		);
	}

}
