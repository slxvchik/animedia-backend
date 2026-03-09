package dev.animedia.contentservice.content.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import dev.animedia.contentservice.content.model.ContentTranslation;

@Repository
public interface ContentTranslationRepository extends JpaRepository<ContentTranslation, UUID> {
	@Query(
		"SELECT ct " + 
		"FROM ContentTranslation as ct " +
		"WHERE (:contentUuid IS NULL OR ct.content.uuid = :contentUuid) " + 
		"AND (:title IS NULL OR LOWER(ct.title) LIKE CONCAT('%', LOWER(:title), '%'))"
	)
	Page<ContentTranslation> search(
		@Param("contentUuid") UUID contentUuid,
		@Param("title") String title,
		Pageable pageable
	);
	ContentTranslation findByContentUuidAndLanguageCode(UUID contentUuid, String languageCode);
	@Query(
		"SELECT ct " + 
		"FROM ContentTranslation as ct " +
		"WHERE ct.content.uuid IN (:contentUuids) " +
		"AND ct.languageCode = :languageCode"
	)
	List<ContentTranslation> findByContentUuidsAndLanguageCode(
		@Param("contentUuids") List<UUID> contentUuids,
		@Param("languageCode") String languageCode
	);
	boolean existsByContentIdAndLanguageCode(UUID contentUuid, String languageCode);
}
