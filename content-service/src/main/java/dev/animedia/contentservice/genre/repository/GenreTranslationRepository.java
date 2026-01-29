package dev.animedia.contentservice.genre.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.animedia.contentservice.genre.model.GenreTranslation;

@Repository
public interface GenreTranslationRepository extends JpaRepository<Long, GenreTranslation>{
}
