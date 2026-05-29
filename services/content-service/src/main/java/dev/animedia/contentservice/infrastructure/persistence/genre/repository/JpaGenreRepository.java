package dev.animedia.contentservice.infrastructure.persistence.genre.repository;

import dev.animedia.contentservice.infrastructure.persistence.genre.model.GenreEntity;
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
        "AND (:lang IS NULL OR gte.languageCode = :lang) " +
        "AND (:active IS NULL OR :active = ge.active)")
    GenreEntity findById(
        @Param("id") UUID id,
        @Param("lang") @Nullable String languageCode,
        @Param("active") @Nullable Boolean active
    );

    @Query("SELECT ge " +
        "FROM GenreEntity ge " +
        "LEFT JOIN FETCH ge.translations gte " +
        "WHERE (ge.id IN :idList) " +
        "AND (:lang IS NULL OR gte.languageCode = :lang) " +
        "AND (:active IS NULL OR :active = ge.active) " +
        "ORDER BY ge.sortOrder DESC")
    List<GenreEntity> findByIdList(
        @Param("idList") List<UUID> idList,
        @Param("lang") @Nullable String languageCode,
        @Param("active") @Nullable Boolean active
    );

    @Query("SELECT ge " +
        "FROM GenreEntity ge " +
        "JOIN FETCH ge.translations gte " +
        "WHERE (:alias IS NULL OR :alias LIKE CONCAT('%', ge.alias, '%')) " +
        "AND (:name IS NULL OR :name LIKE CONCAT('%', gte.name, '%')) " +
        "AND (:desc IS NULL OR :desc LIKE CONCAT('%', gte.description, '%')) " +
        "AND (:lang IS NULL OR :lang = gte.languageCode) " +
        "AND (:active IS NULL OR :active = ge.active)")
    Page<GenreEntity> search(
        @Param("active") @Nullable Boolean active,
        @Param("alias") @Nullable String alias,
        @Param("name") @Nullable String name,
        @Param("desc") @Nullable String description,
        @Param("lang") @Nullable String languageCode,
        Pageable pageable
    );

    boolean existsByAlias(String alias);
}