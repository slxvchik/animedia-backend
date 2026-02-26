package dev.animedia.contentservice.content.repository;

import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.model.ContentTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContentTranslationRepository extends JpaRepository<ContentTranslation, UUID> {
	ContentTranslation findByContentUuidAndLanguageCode(UUID contentUuid, String languageCode);
}
