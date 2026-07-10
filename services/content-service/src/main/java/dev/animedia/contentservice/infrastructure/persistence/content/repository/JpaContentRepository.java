package dev.animedia.contentservice.infrastructure.persistence.content.repository;

import dev.animedia.contentservice.domain.content.model.ContentType;
import dev.animedia.contentservice.infrastructure.persistence.content.model.ContentEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaContentRepository extends JpaRepository<ContentEntity, UUID>, JpaSpecificationExecutor<ContentEntity> {

	@Query(
		"SELECT ce " +
		"FROM ContentEntity ce " +
		"LEFT JOIN FETCH ce.translations t " +
		"WHERE ce.id = :id " +
		"AND (:lang IS NULL OR t.languageCode = :lang)"
	)
	Optional<ContentEntity> findById(
		@Param("id")
		UUID id,
		@Param("lang")
		@Nullable
		String languageCode
	);

	@Query(
		"SELECT ce " +
		"FROM ContentEntity ce " +
		"LEFT JOIN FETCH ce.translations t " +
		"WHERE ce.alias = :alias " +
		"AND ce.contentType = :type " +
		"AND ce.season = :season " +
		"AND (:lang IS NULL OR t.languageCode = :lang)"
	)
	Optional<ContentEntity> findByAliasAndTypeAndSeason(
		@Param("alias")
		String alias,
		@Param("type")
		ContentType type,
		@Param("season")
		int season,
		@Param("lang")
		@Nullable
		String languageCode
	);

	List<ContentEntity> findByIdListAndLanguageCode(
		@Param("ids")
		List<UUID> idList,
		@Param("lang")
		@Nullable
		String languageCode
	);

	boolean existsByAliasAndContentTypeAndSeason(String alias, ContentType type, int season);
}
