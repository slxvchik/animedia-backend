package dev.animedia.contentservice.genre.repository;

import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.language.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.animedia.contentservice.genre.model.GenreTranslation;
import org.yaml.snakeyaml.util.Tuple;

import java.util.List;

@Repository
public interface GenreTranslationRepository extends JpaRepository<GenreTranslation, Long>{

    boolean existsByGenreIdAndLanguageCode(Long genreId, String languageCode);

    @Query("SELECT count(gt) > 0 FROM GenreTranslation gt WHERE (gt.genre.id, gt.language.code) IN :pairs")
    boolean existsByGenreIdAndLanguageCodePairs(@Param("pairs") List<Tuple<Genre, Language>> pairs);

}
