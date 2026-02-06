package dev.animedia.contentservice.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.animedia.contentservice.genre.model.GenreTranslation;

import java.util.List;
import java.util.Optional;

@Repository
public interface GenreTranslationRepository extends JpaRepository<GenreTranslation, Long>{

    List<GenreTranslation> findByIdIn(List<Long> ids);
    List<GenreTranslation> findByGenreIdIn(List<Long> genreIds);
    List<GenreTranslation> findByGenreIdInAndLanguageCode(List<Long> genreIds, String languageCode);

    Optional<GenreTranslation> findByGenreIdAndLanguageCode(Long genreId, String languageCode);

    boolean existsByIdIn(List<Long> ids);
    boolean existsByGenreIdAndLanguageCode(Long genreId, String languageCode);

}
