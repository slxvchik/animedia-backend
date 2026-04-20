package dev.animedia.contentservice.infrastructure.status.persistence.repository;

import dev.animedia.contentservice.infrastructure.status.persistence.model.StatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaStatusQueryRepository extends JpaRepository<StatusEntity, Long> {
	boolean existsByAlias(String alias);
	boolean existsByAliasAndIdNot(String alias, Long id);
}