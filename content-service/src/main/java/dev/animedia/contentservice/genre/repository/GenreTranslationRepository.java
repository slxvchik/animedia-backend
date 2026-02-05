package dev.animedia.contentservice.genre.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.animedia.contentservice.genre.model.GenreTranslation;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreTranslationRepository extends JpaRepository<GenreTranslation, Long>{

    List<GenreTranslation> findByIdIn(List<Long> genreIds);
    Page<GenreTranslation> findByIdIn(List<Long> genreIds, Pageable pageable);

    List<GenreTranslation> findByGenreId(Long genreId);
    Page<GenreTranslation> findByGenreId(Long genreId, Pageable pageable);

    List<GenreTranslation> findByLanguageCode(String languageCode);
    Page<GenreTranslation> findByLanguageCode(String languageCode, Pageable pageable);

    Page<GenreTranslation> findByLanguageCodeIn(List<String> languageCodes, Pageable pageable);

    Optional<GenreTranslation> findByGenreIdAndLanguageCode(Long genreId, String languageCode);
    List<GenreTranslation> findByGenreIdInAndLanguageCode(List<Long> genreIds, String languageCode);
    Page<GenreTranslation> findByGenreIdInAndLanguageCode(List<Long> genreIds, String languageCode, Pageable pageable);

    @Query("SELECT gt FROM GenreTranslation gt WHERE (gt.genre.id, gt.language.code) IN :pairs")
    List<GenreTranslation> findByGenreIdsAndLanguageCodes(@Param("pairs") List<Object[]> pairs);

    boolean existsByIdIn(List<Long> ids);
    boolean existsByGenreIdAndLanguageCode(Long genreId, String languageCode);

    @Query("SELECT count(gt) > 0 FROM GenreTranslation gt WHERE (gt.genre.id, gt.language.code) IN :pairs")
    boolean existsByGenreIdAndLanguageCodePairs(@Param("pairs") List<Object[]> pairs);


    List<GenreTranslation> findByGenreIdIn(List<Long> genreIds);

    Page<GenreTranslation> findByNameLike(String name, Pageable pageable);
}
