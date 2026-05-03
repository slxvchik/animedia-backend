package dev.animedia.languageservice.infrastructure.persistence.repository;

import dev.animedia.languageservice.infrastructure.persistence.model.LanguageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaLanguageRepository extends JpaRepository<LanguageEntity, String> {
}
