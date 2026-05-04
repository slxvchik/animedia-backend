package dev.animedia.contentservice.infrastructure.content.persistence.repository;

import dev.animedia.contentservice.infrastructure.content.persistence.model.ContentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaContentRepository extends JpaRepository<ContentEntity, UUID> {

	@Query("SELECT ce " +
		"FROM ContentEntity ce " +
		"LEFT JOIN FETCH ce.genreSet g " +
		"LEFT JOIN FETCH ce.translationSet t " +
		"WHERE ce.id = :id AND t.languageCode = :lang")
	Optional<ContentEntity> findById(
		@Param("id") UUID id,
		@Param("lang") String languageCode
	);
}
