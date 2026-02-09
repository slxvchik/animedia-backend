package dev.animedia.contentservice.contentstatus.repository;

import dev.animedia.contentservice.contentstatus.model.ContentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentStatusRepository extends JpaRepository<ContentStatus, Long> {
}
