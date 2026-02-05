package dev.animedia.contentservice.genre.controller;

import dev.animedia.contentservice.app.context.LocaleLanguageContext;
import dev.animedia.contentservice.app.dto.AppResponseDto;
import dev.animedia.contentservice.app.dto.PagedResponse;
import dev.animedia.contentservice.app.mapper.PageMapper;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.service.GenrePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

	private final GenrePageService genrePageService;
	private final PageMapper pageMapper;

	@Autowired
	public GenreController(GenrePageService genrePageService, PageMapper pageMapper) {
		this.genrePageService = genrePageService;
		this.pageMapper = pageMapper;
	}

	@GetMapping("/search")
	public ResponseEntity<AppResponseDto<PagedResponse<GenreWithTranslationResponseDto>>> search(
		@PageableDefault(sort = {"sort"})
		Pageable pageable,
		@RequestParam(required = false)
		String alias
	) {
		String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
		Page<GenreWithTranslationResponseDto> genresTranslation = genrePageService.search(alias, languageCode, pageable);
		return ResponseEntity.ok(
			AppResponseDto.success(
				pageMapper.toPagedResponse(genresTranslation)
			)
		);
	}
}
