package dev.animedia.contentservice.genre;

import dev.animedia.contentservice.app.dto.AppResponseDto;
import dev.animedia.contentservice.genre.dto.request.CreateGenreRequestDto;
import dev.animedia.contentservice.genre.dto.response.GenreResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.exception.GenreInvalidSearchTypeException;
import dev.animedia.contentservice.genre.service.GenreCommandService;
import dev.animedia.contentservice.genre.service.GenreQueryService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    private enum GenreSearchType {
        ID,
        ALIAS
    }

    private final GenreQueryService genreQueryService;
    private final GenreCommandService genreCommandService;

    @Autowired
    public GenreController(
        GenreQueryService genreQueryService,
        GenreCommandService genreCommandService
    ) {
        this.genreQueryService = genreQueryService;
        this.genreCommandService = genreCommandService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<GenreWithTranslationResponseDto>> getAll(
        @PageableDefault(sort = {"sort"})
        Pageable pageable
    ) {
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<AppResponseDto<Page<GenreWithTranslationsResponseDto>>> search(
        @PageableDefault(sort = {"sort"})
        Pageable pageable,
        @RequestParam GenreSearchType type,
        @RequestParam String values
    ) {

        List<String> valuesRequest = List.of(values.split(","));
        Page<GenreWithTranslationsResponseDto> genres;
        switch (type) {
            case ID -> {

                var genreIds = valuesRequest.stream().map(Long::parseLong).toList();

                genres = genreQueryService.findByIds(genreIds, pageable);
            }
            case ALIAS -> genres = genreQueryService.findByAliases(valuesRequest, pageable);
            default -> throw new GenreInvalidSearchTypeException();
        }
        return ResponseEntity.ok(AppResponseDto.success(genres));
    }

    @PostMapping
    public ResponseEntity<AppResponseDto<GenreResponseDto>> create(
        @RequestBody @Validated CreateGenreRequestDto createGenreRequestDto
    ) {

        var createdGenre = genreCommandService.create(createGenreRequestDto);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(AppResponseDto.success(createdGenre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppResponseDto<Page<GenreWithTranslationResponseDto>>> update(@PathVariable Long id, @RequestBody Genre genre) {
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AppResponseDto<Void>> delete(@PathVariable Long id) {
        return ResponseEntity.ok(AppResponseDto.success(null));
    }
}
