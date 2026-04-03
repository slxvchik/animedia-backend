package dev.animedia.contentservice.old.status.repository;

import dev.animedia.contentservice.old.status.model.ContentStatusTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentStatusTranslationRepository extends JpaRepository<ContentStatusTranslation, Long> {
    ContentStatusTranslation findByContentStatusIdAndLanguageCode(Long contentStatusId, String languageCode);
    boolean existsByContentStatusIdAndLanguageCode(Long contentStatusId, String languageCode);
}
