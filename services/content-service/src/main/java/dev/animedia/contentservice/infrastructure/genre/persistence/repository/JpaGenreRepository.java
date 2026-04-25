package dev.animedia.contentservice.infrastructure.genre.persistence.repository;

import dev.animedia.contentservice.infrastructure.genre.persistence.dto.GenreTranslationRowDto;
import dev.animedia.contentservice.infrastructure.genre.persistence.model.GenreEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.Nullable;
import java.util.List;

@Repository
public interface JpaGenreRepository extends JpaRepository<GenreEntity, Long> {

    @Query("SELECT new dev.animedia.contentservice.infrastructure.genre.persistence.dto.GenreTranslationRowDto(" +
        "ge.id, ge.alias, ge.sortOrder, gte.id, gte.languageCode, gte.name, gte.description) " +
        "FROM GenreEntity AS ge " +
        "LEFT JOIN GenreTranslationEntity AS gte " +
        "ON ge.id = gte.genreEntity.id " +
        "WHERE ge.id = :id " +
        "AND (:lang IS NULL OR gte.languageCode = :lang)")
    List<GenreTranslationRowDto> findById(
        @Param("id") Long id,
        @Param("lang") @Nullable String languageCode
    );

    @Query("SELECT new dev.animedia.contentservice.infrastructure.genre.persistence.dto.GenreTranslationRowDto(" +
        "ge.id, ge.alias, ge.sortOrder, gte.id, gte.languageCode, gte.name, gte.description) " +
        "FROM GenreEntity AS ge " +
        "LEFT JOIN GenreTranslationEntity AS gte " +
        "ON ge.id = gte.genreEntity.id " +
        "WHERE (ge.id IN :idList) " +
        "AND (:lang IS NULL OR gte.languageCode = :lang)")
    List<GenreTranslationRowDto> findByIdListAndLanguageCode(
        @Param("idList") List<Long> idList,
        @Param("lang") @Nullable String languageCode
    );

    @Query("SELECT DISTINCT ge.id " +
        "FROM GenreEntity AS ge " +
        "JOIN GenreTranslationEntity AS gte " +
        "ON ge.id = gte.genreEntity.id " +
        "WHERE (:alias IS NULL OR :alias LIKE CONCAT('%', ge.alias, '%')) " +
        "AND (:name IS NULL OR :name LIKE CONCAT('%', gte.name, '%')) " +
        "AND (:desc IS NULL OR :desc LIKE CONCAT('%', gte.description, '%')) " +
        "AND (:lang IS NULL OR :lang = gte.languageCode)")
    Page<Long> search(
        @Param("alias") @Nullable String alias,
        @Param("name") @Nullable String name,
        @Param("desc") @Nullable String description,
        @Param("lang") @Nullable String languageCode,
        Pageable pageable
    );

    boolean existsByAlias(String alias);
    boolean existsByAliasAndIdNot(String alias, Long id);
}