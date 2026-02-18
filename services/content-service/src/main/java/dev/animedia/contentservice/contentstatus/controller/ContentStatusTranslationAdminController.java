package dev.animedia.contentservice.contentstatus.controller;

import dev.animedia.contentservice.app.dto.AppResponseDto;
import dev.animedia.contentservice.app.dto.ContentResponse;
import dev.animedia.contentservice.app.dto.PagedResponse;
import dev.animedia.contentservice.contentstatus.dto.request.ContentStatusRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.CreateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.SearchContentStatusAdminRequestDto;
import dev.animedia.contentservice.contentstatus.dto.request.UpdateContentStatusTranslationRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationsResponseDto;
import dev.animedia.contentservice.contentstatus.mapper.ContentStatusMapper;
import dev.animedia.contentservice.contentstatus.service.ContentStatusCommandService;
import dev.animedia.contentservice.contentstatus.service.ContentStatusPageService;
import dev.animedia.contentservice.contentstatus.service.ContentStatusTranslationCommandService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/v1/content-status/translations")
public class ContentStatusTranslationAdminController {

	private final ContentStatusTranslationCommandService contentStatusTranslationCommandService;

	@Autowired
	public ContentStatusTranslationAdminController(ContentStatusTranslationCommandService contentStatusTranslationCommandService) {
		this.contentStatusTranslationCommandService = contentStatusTranslationCommandService;
	}

	@PostMapping
	public ResponseEntity<AppResponseDto<ContentResponse<ContentStatusTranslationResponseDto>>> create(
		CreateContentStatusTranslationRequestDto createContentStatusTranslationRequestDto
	) {
		var createdContentStatusResponseDto = contentStatusTranslationCommandService.create(createContentStatusTranslationRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(
			AppResponseDto.success(
				ContentResponse.content(createdContentStatusResponseDto)
			)
		);
	}

	@PutMapping("/{id}")
	public ResponseEntity<AppResponseDto<ContentResponse<ContentStatusTranslationResponseDto>>> update(
		@PathVariable
		@NotNull
		Long id,
		UpdateContentStatusTranslationRequestDto updateContentStatusTranslationRequestDto
	) {
		var updatedContentStatusTranslationResponseDto = contentStatusTranslationCommandService.update(id, updateContentStatusTranslationRequestDto);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content(updatedContentStatusTranslationResponseDto)
			)
		);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AppResponseDto<ContentResponse<Void>>> delete(
		@PathVariable
		@NotNull
		Long id
	) {
		contentStatusTranslationCommandService.delete(id);
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content()
			)
		);
	}
}
