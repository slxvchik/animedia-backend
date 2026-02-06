package dev.animedia.contentservice.language.repository;

import dev.animedia.contentservice.language.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends JpaRepository<Language, String> {
	boolean existsByNameAndCodeIsNot(String name, String code);
}
