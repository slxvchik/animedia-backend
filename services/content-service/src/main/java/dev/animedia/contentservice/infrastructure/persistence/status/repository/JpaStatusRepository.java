package dev.animedia.contentservice.infrastructure.persistence.status.repository;

import dev.animedia.contentservice.infrastructure.persistence.status.dto.StatusTranslationRowDto;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusEntity;
import org.jspecify.annotations.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaStatusRepository extends JpaRepository<StatusEntity, Long> {
	boolean existsByAlias(String alias);
	boolean existsByAliasAndIdNot(String alias, Long id);

	@Query("SELECT DISTINCT new dev.animedia.contentservice.infrastructure.status.persistence.dto.StatusTranslationRowDto(se.id, se.alias, se.sortOrder, ste.id, ste.languageCode, ste.name) " +
		"FROM StatusEntity se " +
		"LEFT JOIN StatusTranslationEntity ste " +
		"ON se.id = ste.statusEntity.id " +
		"AND (:languageCode IS NULL OR :languageCode = ste.languageCode) " +
		"WHERE se.id = :id")
	List<StatusTranslationRowDto> findByIdAndLanguageCode(
		@Param("id") Long id,
		@Param("languageCode") @Nullable String languageCode
	);

	@Query("SELECT DISTINCT new dev.animedia.contentservice.infrastructure.status.persistence.dto.StatusTranslationRowDto(se.id, se.alias, se.sortOrder, ste.id, ste.languageCode, ste.name) " +
		"FROM StatusEntity se " +
		"LEFT JOIN StatusTranslationEntity ste " +
		"ON se.id = ste.statusEntity.id " +
		"AND (:languageCode IS NULL OR :languageCode = ste.languageCode) " +
		"WHERE se.id IN :idList")
	List<StatusTranslationRowDto> findByIdListAndLanguageCode(
		@Param("idList") List<Long> idList,
		@Param("languageCode") @Nullable String languageCode
	);

	@Query("SELECT DISTINCT se.id " +
		"FROM StatusEntity se " +
		"JOIN StatusTranslationEntity ste " +
		"ON se.id = ste.statusEntity.id " +
		"WHERE (:alias IS NULL OR LOWER(:alias) LIKE CONCAT('%', LOWER(se.alias), '%')) " +
		"AND (:name IS NULL OR LOWER(:name) LIKE CONCAT('%', LOWER(ste.name), '%')) " +
		"AND (:lang IS NULL OR LOWER(:lang) = LOWER(ste.languageCode))")
	Page<Long> search(
		@Param("alias") String alias,
		@Param("name") String name,
		@Param("lang") String languageCode,
		Pageable pageable
	);
}