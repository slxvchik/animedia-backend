package dev.animedia.contentservice.status.infrastracture.persistence.repository;

import dev.animedia.contentservice.status.infrastracture.persistence.model.StatusEntity;
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

	@Query("SELECT DISTINCT se " +
		"FROM StatusEntity se " +
		"LEFT JOIN FETCH se.translations ste " +
		"WHERE se.id = :id " +
		"AND (:languageCode IS NULL OR :languageCode = ste.languageCode)")
	StatusEntity findById(
		@Param("id") UUID id,
		@Param("languageCode") @Nullable String languageCode
	);

	@Query("SELECT DISTINCT se " +
		"FROM StatusEntity se " +
		"LEFT JOIN FETCH se.translations ste " +
		"WHERE se.id IN :idList " +
		"AND (:languageCode IS NULL OR :languageCode = ste.languageCode) " +
		"ORDER BY se.sortOrder DESC")
	List<StatusEntity> findByIdList(
		@Param("idList") List<UUID> idList,
		@Param("languageCode") @Nullable String languageCode
	);

	@Query("SELECT DISTINCT se " +
		"FROM StatusEntity se " +
		"JOIN FETCH se.translations ste")
	Page<StatusEntity> findByPageable(
		Pageable pageable
	);

	boolean existsByAlias(String alias);
}