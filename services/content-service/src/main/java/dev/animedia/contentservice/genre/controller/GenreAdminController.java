package dev.animedia.contentservice.genre.controller;

import dev.animedia.contentservice.app.dto.AppResponseDto;
import dev.animedia.contentservice.app.dto.ContentResponse;
import dev.animedia.contentservice.app.dto.PagedResponse;
import dev.animedia.contentservice.app.exception.AppExceptionConstants;
import dev.animedia.contentservice.genre.GenreConstants;
import dev.animedia.contentservice.genre.dto.request.GenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import dev.animedia.contentservice.genre.service.GenreCommandService;
import dev.animedia.contentservice.genre.service.GenrePageService;

import jakarta.validation.constraints.*;
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
@RequestMapping("/api/v1/admin/genre")
public class GenreAdminController {

    private final GenrePageService genrePageService;
    private final GenreCommandService genreCommandService;

    @Autowired
    public GenreAdminController(
        GenrePageService genrePageService,
        GenreCommandService genreCommandService
    ) {
        this.genrePageService = genrePageService;
        this.genreCommandService = genreCommandService;
    }

    @GetMapping("/search")
    public ResponseEntity<AppResponseDto<PagedResponse<GenreWithTranslationsResponseDto>>> search(
        @PageableDefault(sort = {"sort"})
        Pageable pageable,
        @RequestParam(required = false)
        String alias,
        @RequestParam(required = false)
        List<String> languageCodes,
        @RequestParam(required = false)
        String name
    ) {
        Page<GenreWithTranslationsResponseDto> genresTranslations = genrePageService.search(alias, languageCodes, name, pageable);
        return ResponseEntity.ok(
            AppResponseDto.success(
                PagedResponse.getPagedResponse(genresTranslations)
            )
        );
    }

    @PostMapping
    public ResponseEntity<AppResponseDto<ContentResponse<GenreResponseDto>>> create(
        @RequestBody
        @Validated
        GenreRequestDto genreRequestDto
    ) {
        var createdGenre = genreCommandService.create(genreRequestDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                AppResponseDto.success(
                    ContentResponse.content(createdGenre)
                )
            );
    }

    @PostMapping("/batch")
    public ResponseEntity<AppResponseDto<ContentResponse<List<GenreResponseDto>>>> batchCreate(
        @RequestBody
        @Validated
        List<GenreRequestDto> createGenresRequestDto
    ) {
        var createdGenres = genreCommandService.create(createGenresRequestDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                AppResponseDto.success(
                    ContentResponse.content(createdGenres)
                )
            );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponseDto<ContentResponse<GenreResponseDto>>> update(
        @PathVariable
        Long id,
        @RequestBody
        @Validated
        GenreRequestDto genreRequestDto
    ) {
        var updatedGenre = genreCommandService.update(id, genreRequestDto);
        return ResponseEntity.ok(
            AppResponseDto.success(
                ContentResponse.content(updatedGenre)
            )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponseDto<ContentResponse<Void>>> delete(
        @PathVariable
        @NotNull(message = GenreConstants.GENRE_ID_REQUIRED_MESSAGE)
        Long id
    ) {
        genreCommandService.delete(id);
        return ResponseEntity.ok(
            AppResponseDto.success(ContentResponse.content())
        );
    }

    @DeleteMapping("/batch")
    public ResponseEntity<AppResponseDto<ContentResponse<Void>>> batchDelete(
        @RequestParam
        @NotNull(message = GenreConstants.GENRE_ID_REQUIRED_MESSAGE)
        @Size(max = 100, message = AppExceptionConstants.BATCH_SIZE_LIMIT_MESSAGE)
        List<Long> ids
    ) {
        genreCommandService.delete(ids);
        return ResponseEntity.ok(
            AppResponseDto.success(ContentResponse.content())
        );
    }
}
