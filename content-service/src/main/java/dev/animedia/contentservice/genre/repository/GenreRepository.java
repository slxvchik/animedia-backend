package dev.animedia.contentservice.genre.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.animedia.contentservice.genre.model.Genre;

import java.util.List;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Page<Genre> findByIdIn(List<Long> ids, Pageable pageable);
    Page<Genre> findByAliasLike(String alias, Pageable pageable);
    Page<Genre> findByAliasIn(List<String> aliases, Pageable pageable);

    List<Genre> findByIdIn(List<Long> ids);
    List<Genre> findByAliasIn(List<String> aliases);

    boolean existsByIdIn(List<Long> ids);
    boolean existsByAlias(String alias);
    boolean existsByAliasIn(List<String> aliases);

    Long id(Long id);
}
