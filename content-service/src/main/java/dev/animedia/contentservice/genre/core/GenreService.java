package dev.animedia.contentservice.genre.core;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenreService {
    List<Genre> getAllGenres(Pageable pageable);
    List<Genre> getGenresByIds(Pageable pageable, List<String> ids);
    List<Genre> getGenresByAliases(Pageable pageable, List<String> aliases);

    Genre saveGenre(Genre genre);
    Genre updateGenre(Genre genre);
    void deleteGenre(Long id);
}
