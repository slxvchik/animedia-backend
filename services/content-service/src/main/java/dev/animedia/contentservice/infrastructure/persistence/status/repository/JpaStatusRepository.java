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
import java.util.UUID;

@Repository
public interface JpaStatusRepository extends JpaRepository<StatusEntity, UUID> {

	@Query("SELECT se " +
		"FROM StatusEntity se " +
		"LEFT JOIN FETCH se.translations ste " +
		"WHERE se.id = :id " +
		"AND (:languageCode IS NULL OR :languageCode = ste.languageCode) " +
		"AND (:active IS NULL OR :active = se.active)")
	StatusEntity findById(
		@Param("id") UUID id,
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
		@Param("idList") List<UUID> idList,
		@Param("active") @Nullable Boolean active,
		@Param("languageCode") @Nullable String languageCode
	);

	@Query("SELECT DISTINCT se " +
		"FROM StatusEntity se " +
		"JOIN FETCH se.translations ste")
	Page<StatusEntity> findAll(
		Pageable pageable
	);

	boolean existsByAlias(String alias);
}