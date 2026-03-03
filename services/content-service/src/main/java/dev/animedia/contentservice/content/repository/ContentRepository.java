package dev.animedia.contentservice.content.repository;

import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.model.ContentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ContentRepository extends JpaRepository<Content, UUID>, JpaSpecificationExecutor<Content> {
	Optional<Content> findByAlias(String alias);
	boolean existsByAliasAndTypeAndSeason(String alias, ContentType type, Integer season);
	boolean existsByAliasAndTypeAndSeasonAndUuidIsNot(String alias, ContentType type, Integer season, UUID uuid);
}
