package dev.animedia.contentservice.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.animedia.contentservice.genre.model.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    List<Genre> findByIdIn(List<Long> ids);
    List<Genre> findByAliasIn(List<String> aliases);

    boolean existsByIdIn(List<Long> ids);
    boolean existsByAlias(String alias);
    boolean existsByAliasIn(List<String> aliases);
}
