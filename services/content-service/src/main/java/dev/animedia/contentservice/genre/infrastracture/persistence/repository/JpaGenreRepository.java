package dev.animedia.contentservice.genre.infrastracture.persistence.repository;

import dev.animedia.contentservice.genre.infrastracture.persistence.model.GenreEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaGenreRepository extends JpaRepository<GenreEntity, UUID> {

    @Query("SELECT ge " +
        "FROM GenreEntity ge " +
        "LEFT JOIN FETCH ge.translations gte " +
        "WHERE ge.id = :id " +
        "AND (:lang IS NULL OR gte.languageCode = :lang)")
    GenreEntity findById(
        @Param("id") UUID id,
        @Param("lang") @Nullable String languageCode
    );

    @Query(
        "SELECT ge " +
        "FROM GenreEntity ge " +
        "LEFT JOIN FETCH ge.translations gte " +
        "WHERE (ge.id IN :idList) " +
        "AND (:lang IS NULL OR gte.languageCode = :lang)"
    )
    List<GenreEntity> findByIdList(
        @Param("idList") List<UUID> idList,
        @Param("lang") @Nullable String languageCode
    );

    @Query(
        "SELECT ge " +
        "FROM GenreEntity ge " +
        "JOIN FETCH ge.translations gte"
    )
    Page<GenreEntity> findByPageable(Pageable pageable);

    boolean existsByAlias(String alias);
}