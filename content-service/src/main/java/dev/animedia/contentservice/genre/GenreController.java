package dev.animedia.contentservice.genre;

import dev.animedia.contentservice.app.context.LocaleLanguageContext;
import dev.animedia.contentservice.app.dto.AppResponseDto;
import dev.animedia.contentservice.genre.dto.request.CreateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.request.UpdateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import dev.animedia.contentservice.genre.exception.GenreSearchBatchSizeLimitException;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.exception.GenreInvalidSearchTypeException;
import dev.animedia.contentservice.genre.service.GenreCommandService;
import dev.animedia.contentservice.genre.service.GenrePageService;
import dev.animedia.contentservice.genre.service.GenreQueryService;

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
@RequestMapping("/api/v1/genre")
public class GenreController {

    public enum GenreSearchType {
        ID,
        ALIAS
    }

    private final GenrePageService genrePageService;
    private final GenreCommandService genreCommandService;

    @Autowired
    public GenreController(
        GenrePageService genrePageService,
        GenreCommandService genreCommandService
    ) {
        this.genrePageService = genrePageService;
        this.genreCommandService = genreCommandService;
    }

    @GetMapping("/all")
    public ResponseEntity<AppResponseDto<Page<GenreWithTranslationsResponseDto>>> getAll(
        @PageableDefault(sort = {"sort"})
        Pageable pageable
    ) {
        var allGenres = genrePageService.findAll(pageable);
        return ResponseEntity.ok(
            AppResponseDto.success(allGenres)
        );
    }

    @GetMapping("/language")
    public ResponseEntity<AppResponseDto<Page<GenreWithTranslationResponseDto>>> getAllByLanguage(
        @PageableDefault
        Pageable pageable
    ) {
        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
        var genresByLanguage = genrePageService.findByLanguage(languageCode, pageable);
        return ResponseEntity.ok(
            AppResponseDto.success(genresByLanguage)
        );
    }

    @GetMapping("/search")
    public ResponseEntity<AppResponseDto<Page<GenreWithTranslationsResponseDto>>> search(
        @PageableDefault(sort = {"sort"})
        Pageable pageable,
        @RequestParam
        GenreSearchType type,
        @RequestParam
        @NotNull(message = GenreConstants.GENRE_SEARCH_VALUES_REQUIRED_MESSAGE)
        @Min(value = 1, message = GenreConstants.GENRE_SEARCH_VALUES_REQUIRED_MESSAGE)
        @Max(value = 100, message = GenreConstants.GENRE_SEARCH_BATCH_SIZE_LIMIT_MESSAGE)
        List<String> values
    ) {

        Page<GenreWithTranslationsResponseDto> genres;

        switch (type) {
            case ID -> {
                var genreIds = values.stream()
                    .map(Long::parseLong)
                    .toList();
                genres = genrePageService.findByIds(genreIds, pageable);
            }
            case ALIAS -> genres = genrePageService.findByAliases(values, pageable);
            default -> throw new GenreInvalidSearchTypeException();
        }

        return ResponseEntity.ok(
            AppResponseDto.success(genres)
        );
    }

    @GetMapping("/search/language")
    public ResponseEntity<AppResponseDto<Page<GenreWithTranslationResponseDto>>> searchByLanguage(
        @PageableDefault(sort = {"sort"})
        Pageable pageable,
        @RequestParam
        GenreSearchType type,
        @RequestParam
        @NotNull(message = GenreConstants.GENRE_SEARCH_VALUES_REQUIRED_MESSAGE)
        @Min(value = 1, message = GenreConstants.GENRE_SEARCH_VALUES_REQUIRED_MESSAGE)
        @Max(value = 100, message = GenreConstants.GENRE_SEARCH_BATCH_SIZE_LIMIT_MESSAGE)
        List<String> values
    ) {

        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
        Page<GenreWithTranslationResponseDto> genres;

        switch (type) {
            case ID -> {
                var genreIds = values.stream()
                    .map(Long::parseLong)
                    .toList();
                genres = genreQueryService.findByIdsAndLanguageCode(genreIds, languageCode, pageable);
            }
            case ALIAS -> genres = genreQueryService.findByAliasesAndLanguage(values, languageCode, pageable);
            default -> throw new GenreInvalidSearchTypeException();
        }

        return ResponseEntity.ok(
            AppResponseDto.success(genres)
        );
    }

    @PostMapping
    public ResponseEntity<AppResponseDto<GenreResponseDto>> create(
        @RequestBody
        @Validated
        CreateGenreRequestDto createGenreRequestDto
    ) {
        var createdGenre = genreCommandService.create(createGenreRequestDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(AppResponseDto.success(createdGenre));
    }

    @PostMapping("/batch")
    public ResponseEntity<AppResponseDto<List<GenreResponseDto>>> batchCreate(
        @RequestBody
        @Validated
        List<CreateGenreRequestDto> createGenresRequestDto
    ) {
        var createdGenres = genreCommandService.create(createGenresRequestDto);
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(AppResponseDto.success(createdGenres));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponseDto<GenreResponseDto>> update(
        @PathVariable
        @NotNull(message = GenreConstants.GENRE_ID_REQUIRED_MESSAGE)
        Long id,
        @RequestBody
        @Validated
        UpdateGenreRequestDto updateGenreRequestDto
    ) {
        var updatedGenre = genreCommandService.update(updateGenreRequestDto);
        return ResponseEntity.ok(
            AppResponseDto.success(updatedGenre)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponseDto<Void>> delete(
        @PathVariable
        @NotNull(message = GenreConstants.GENRE_ID_REQUIRED_MESSAGE)
        Long id
    ) {
        genreCommandService.delete(id);
        return ResponseEntity.ok(
            AppResponseDto.success(null)
        );
    }

    @DeleteMapping("/batch")
    public ResponseEntity<AppResponseDto<Void>> batchDelete(
        @RequestParam
        @NotNull(message = GenreConstants.GENRE_ID_REQUIRED_MESSAGE)
        List<Long> ids
    ) {
        genreCommandService.delete(ids);
        return ResponseEntity.ok(
            AppResponseDto.success(null)
        );
    }
}
