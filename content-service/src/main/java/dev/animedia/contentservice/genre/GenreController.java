package dev.animedia.contentservice.genre;

import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.exception.GenreInvalidSearchTypeException;
import dev.animedia.contentservice.genre.service.GenreQueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {

    private enum GenreSearchType {
        ID,
        ALIAS
    }

    private final GenreQueryService genreService;

    @Autowired
    public GenreController(GenreQueryService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<GenreWithTranslationResponseDto>> getAll(
        @PageableDefault(sort = {"sort"})
        Pageable pageable
    ) {
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<GenreWithTranslationResponseDto>> search(
        @PageableDefault(sort = {"sort"})
        Pageable pageable,
        @RequestParam GenreSearchType type,
        @RequestParam String values
    ) {

        List<String> valuesRequest = List.of(values.split(","));
        List<Genre> genres;
        switch (type) {
            case ID -> {
                genres = genreService.getGenresByIds(pageable, valuesRequest);
            }
            case ALIAS -> {
                genres = genreService.getGenresByAliases(pageable, valuesRequest);
            }
            default -> throw new GenreInvalidSearchTypeException();
        }
        return null;
    }

    @PostMapping
    public Genre create(@RequestBody Genre genre) {
        return null;
    }

    @PutMapping("/{id}")
    public Genre update(@PathVariable Long id, @RequestBody Genre genre) {
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {

    }
}
