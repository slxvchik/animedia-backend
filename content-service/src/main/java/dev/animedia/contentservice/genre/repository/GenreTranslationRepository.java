package dev.animedia.contentservice.genre.repository;

import dev.animedia.contentservice.genre.dto.GenreLanguagePair;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.animedia.contentservice.genre.model.GenreTranslation;
import org.yaml.snakeyaml.util.Tuple;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreTranslationRepository extends JpaRepository<GenreTranslation, Long>{

    List<GenreTranslation> findByIdIn(List<Long> genreIds);
    Page<GenreTranslation> findByIdIn(List<Long> genreIds, Pageable pageable);

    List<GenreTranslation> findByGenreId(Long genreId);
    List<GenreTranslation> findByGenreIdIn(List<Long> genreIds);

    Page<GenreTranslation> findByGenreId(Long genreId, Pageable pageable);
    Page<GenreTranslation> findByGenreIdIn(List<Long> genreIds, Pageable pageable);

    List<GenreTranslation> findByLanguageCode(String languageCode);
    List<GenreTranslation> findByLanguageCodeIn(List<String> languageCodes);

    Page<GenreTranslation> findByLanguageCode(String languageCode, Pageable pageable);
    Page<GenreTranslation> findByLanguageCodeIn(List<String> languageCodes, Pageable pageable);

    Optional<GenreTranslation> findByGenreIdAndLanguageCode(Long genreId, String languageCode);
    @Query("SELECT gt FROM GenreTranslation gt WHERE (gt.genre.id, gt.language.code) IN :pairs")
    List<GenreTranslation> findByGenreIdsAndLanguageCodes(@Param("pairs") List<Object[]> pairs);
    @Query("SELECT gt FROM GenreTranslation gt WHERE (gt.genre.id, gt.language.code) IN :pairs")
    Page<GenreTranslation> findByGenreIdsAndLanguageCodes(@Param("pairs") List<Object[]> pairs, Pageable pageable);

    boolean existsByGenreIdAndLanguageCode(Long genreId, String languageCode);

    @Query("SELECT count(gt) > 0 FROM GenreTranslation gt WHERE (gt.genre.id, gt.language.code) IN :pairs")
    boolean existsByGenreIdAndLanguageCodePairs(@Param("pairs") List<Object[]> pairs);
}
