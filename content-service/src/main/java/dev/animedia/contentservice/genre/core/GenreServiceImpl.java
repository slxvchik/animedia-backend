package dev.animedia.contentservice.genre.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAllGenres(Pageable pageable) {
        var genres = genreRepository.findAll(pageable);
        return List.of();
    }

    @Override
    public List<Genre> getGenresByIds(Pageable pageable, List<Long> ids) {
        return List.of();
    }

    @Override
    public List<Genre> getGenresByAliases(Pageable pageable, List<String> aliases) {
        return List.of();
    }

    @Override
    public Genre getGenreById(Long id) {
        return null;
    }

    @Override
    public Genre getGenreByAlias(String alias) {
        return null;
    }

    @Override
    public Genre saveGenre(Genre genre) {
        return null;
    }

    @Override
    public Genre updateGenre(Genre genre) {
        return null;
    }

    @Override
    public void deleteGenre(Long id) {

    }
}
