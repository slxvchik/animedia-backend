package dev.animedia.contentservice.contentstatus.controller;

import dev.animedia.contentservice.app.context.LocaleLanguageContext;
import dev.animedia.contentservice.app.dto.AppResponseDto;
import dev.animedia.contentservice.app.dto.ContentResponse;
import dev.animedia.contentservice.contentstatus.dto.request.SearchContentStatusUserRequestDto;
import dev.animedia.contentservice.contentstatus.dto.response.ContentStatusWithTranslationResponseDto;
import dev.animedia.contentservice.contentstatus.repository.ContentStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/content-status")
public class ContentStatusController {

	private final ContentStatusRepository contentStatusRepository;

	@Autowired
	public ContentStatusController(ContentStatusRepository contentStatusRepository) {
		this.contentStatusRepository = contentStatusRepository;
	}

	@GetMapping("/search")
	public ResponseEntity<AppResponseDto<ContentResponse<List<ContentStatusWithTranslationResponseDto>>>> search(
		SearchContentStatusUserRequestDto searchContentStatusUserRequestDto
	) {
		String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
		var contentStatusWithTranslationResponseDto = contentStatusRepository.search(languageCode, searchContentStatusUserRequestDto.aliases(), searchContentStatusUserRequestDto.names());
		return ResponseEntity.ok(
			AppResponseDto.success(
				ContentResponse.content(contentStatusWithTranslationResponseDto)
			)
		);
	}
}
