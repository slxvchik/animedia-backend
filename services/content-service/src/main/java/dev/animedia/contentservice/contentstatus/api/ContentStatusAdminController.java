package dev.animedia.contentservice.contentstatus.api;

import dev.animedia.contentservice.app.dto.AppResponseDto;
import dev.animedia.contentservice.app.dto.ContentResponse;
import dev.animedia.contentservice.app.dto.PagedResponse;
import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.SearchContentStatusAdminRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.contentservice.contentstatus.service.ContentStatusCommandService;
import dev.animedia.contentservice.contentstatus.service.ContentStatusPageService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/v1/content-status")
public class ContentStatusAdminController {

	private final ContentStatusCommandService contentStatusCommandService;
	private final ContentStatusPageService contentStatusPageService;

	@Autowired
	public ContentStatusAdminController(
		ContentStatusCommandService contentStatusCommandService,
		ContentStatusPageService contentStatusPageService
	) {
		this.contentStatusCommandService = contentStatusCommandService;
		this.contentStatusPageService = contentStatusPageService;
	}

	@GetMapping("/search")
	public ResponseEntity<AppResponseDto<PagedResponse<ContentStatusWithTranslationsResponseDto>>> search(
		SearchContentStatusAdminRequestDto searchContentStatusAdminRequestDto
	) {
		var contentStatusesWithTranslationsResponseDto = contentStatusPageService.search(
			searchContentStatusAdminRequestDto);
		return ResponseEntity.ok(
			AppResponseDto.success(
				PagedResponse.getPagedResponse(contentStatusesWithTranslationsResponseDto)
			)
		);
	}

	@PostMapping
	public ResponseEntity<AppResponseDto<ContentResponse<ContentStatusResponseDto>>> create(
		ContentStatusRequestDto createContentStatusRequestDto
	) {
		var createdContentStatusResponseDto = contentStatusCommandService.create(createContentStatusRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(
			AppResponseDto.success(
				ContentResponse.content(createdContentStatusResponseDto)
			)
		);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AppResponseDto<ContentResponse<ContentStatusResponseDto>>> update(
		@PathVariable
		@NotNull
		Long id,
		ContentStatusRequestDto updateContentStatusRequestDto
	) {
		var updatedContentStatusResponseDto = contentStatusCommandService.update(id, updateContentStatusRequestDto);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content(updatedContentStatusResponseDto)
			)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AppResponseDto<ContentResponse<Void>>> delete(
		@PathVariable
		@NotNull
		Long id
	) {
		contentStatusCommandService.delete(id);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content()
			)
		);
	}
}
