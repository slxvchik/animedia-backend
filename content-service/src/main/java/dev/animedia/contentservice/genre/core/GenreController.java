package dev.animedia.contentservice.genre.core;

import dev.animedia.contentservice.genre.core.dto.GenreResponseDto;
import dev.animedia.contentservice.genre.exception.InvalidGenreSearchTypeException;

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
    private final GenreService genreService;

    private enum GenreSearchType {
        ID,
        ALIAS
    }

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public ResponseEntity<Page<GenreResponseDto>> getAll(
        @PageableDefault(sort = {"sort"})
        Pageable pageable
    ) {
        return null;
    }

    @GetMapping("/search")
    public ResponseEntity<Page<GenreResponseDto>> search(
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
            default -> throw new InvalidGenreSearchTypeException();
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
