package dev.animedia.contentservice.infrastructure.persistence.status.repository;

import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusEntity;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaStatusRepository extends JpaRepository<StatusEntity, Long> {

	@Query("SELECT se " +
		"FROM StatusEntity se " +
		"LEFT JOIN FETCH se.translations ste " +
		"WHERE se.id = :id " +
		"AND (:languageCode IS NULL OR :languageCode = ste.languageCode) " +
		"AND (:active IS NULL OR :active = se.active)")
	StatusEntity findById(
		@Param("id") Long id,
		@Param("active") @Nullable Boolean active,
		@Param("languageCode") @Nullable String languageCode
	);

	@Query("SELECT DISTINCT se " +
		"FROM StatusEntity se " +
		"LEFT JOIN FETCH se.translations ste " +
		"WHERE se.id IN :idList " +
		"AND (:languageCode IS NULL OR :languageCode = ste.languageCode) " +
		"AND (:active IS NULL OR :active = se.active) " +
		"ORDER BY se.sortOrder DESC")
	List<StatusEntity> findByIdList(
		@Param("idList") List<Long> idList,
		@Param("active") @Nullable Boolean active,
		@Param("languageCode") @Nullable String languageCode
	);

	@Query("SELECT DISTINCT se " +
		"FROM StatusEntity se " +
		"JOIN FETCH se.translations ste " +
		"WHERE (CAST(:alias AS string) IS NULL OR LOWER(CAST(:alias AS string)) LIKE CONCAT('%', LOWER(se.alias), '%')) " +
		"AND (CAST(:name AS string) IS NULL OR LOWER(CAST(:name AS string)) LIKE CONCAT('%', LOWER(ste.name), '%')) " +
		"AND (CAST(:lang AS string) IS NULL OR LOWER(CAST(:lang AS string)) = LOWER(ste.languageCode)) " +
		"AND (:active IS NULL OR :active = se.active)")
	Page<StatusEntity> search(
		@Param("active") @Nullable Boolean active,
		@Param("alias") @Nullable String alias,
		@Param("name") @Nullable String name,
		@Param("lang") @Nullable String languageCode,
		Pageable pageable
	);

	boolean existsByAlias(String alias);
}