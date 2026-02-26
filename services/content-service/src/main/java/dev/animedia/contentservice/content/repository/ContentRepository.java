package dev.animedia.contentservice.content.repository;

import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.dto.response.ContentResponseDto;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.model.ContentType;
import dev.animedia.contentservice.status.model.ContentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContentRepository extends JpaRepository<Content, UUID> {
	Optional<Content> findByAlias(String alias);

	@Query("SELECT DISTINCT c " +
		"FROM Content as c LEFT JOIN c.languageCodes lc " +
		"WHERE (:#{#r.uuid} IS NULL OR c.uuid = :#{#r.uuid}) " +
		"AND (:#{#r.alias} IS NULL OR c.alias LIKE \"%:#{#r.alias}%\") " +
		"AND (:#{#r.type} IS NULL OR c.type = :#{#r.type}) " +
		"AND (:#{#r.seasons} IS NULL OR c.season IN (:#{#r.seasons})) " +
		"AND (:#{#r.contentStatuses} IS NULL OR c.status IN (:#{#r.contentStatuses})) " +
		"AND (:#{#r.releaseFrom} IS NULL OR c.releaseDate >= :#{#r.releaseFrom}) " +
		"AND (:#{#r.releaseTo} IS NULL OR c.releaseDate <= :#{#r.releaseTo}) " +
		"AND (:#{#r.createdAtFrom} IS NULL OR c.createdAt >= :#{#r.createdAtFrom}) " +
		"AND (:#{#r.createdAtTo} IS NULL OR c.createdAt <= :#{#r.createdAtTo}) " +
		"AND (:#{#r.updatedAtFrom} IS NULL OR c.updatedAt >= :#{#r.updatedAtFrom}) " +
		"AND (:#{#r.updatedAtTo} IS NULL OR c.updatedAt <= :#{#r.updatedAtTo}) " +
		"AND (:#{#r.active} IS NULL OR c.active = :#{#r.active}) " +
		"AND (:#{#r.languageCodes} IS NULL OR c.languageCodes = :#{#r.languageCodes}) "
	)
	List<Content> search(@Param("r") PrivateSearchRequestDto r);
	List<ContentResponseDto> search(@Param("r") PublicSearchRequestDto r);
	boolean existsByAliasAndTypeAndSeason(String alias, ContentType type, Integer season);
	boolean existsByAliasAndTypeAndSeasonAndUuidIsNot(String alias, ContentType type, Integer season, UUID uuid);
}
