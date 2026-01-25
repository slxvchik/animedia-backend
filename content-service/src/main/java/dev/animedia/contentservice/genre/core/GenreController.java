package dev.animedia.contentservice.genre.core;

import dev.animedia.contentservice.genre.core.dto.GenreSearchType;
import dev.animedia.contentservice.genre.exception.GenreIdNotFoundException;
import dev.animedia.contentservice.genre.exception.InvalidGenreSearchTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/genre")
public class GenreController {
    private final GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public List<Genre> getAll(
            @PageableDefault Pageable pageable
    ) {
        return null;
    }

    @GetMapping("/search")
    public Genre search(
        @PageableDefault Pageable pageable,
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
