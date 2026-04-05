package dev.animedia.languageservice.domain.repository;

import dev.animedia.languageservice.domain.model.Page;
import dev.animedia.languageservice.domain.model.Pageable;
import dev.animedia.languageservice.domain.model.Language;

import java.util.List;
import java.util.Optional;

public interface LanguageQueryRepository {
    Optional<Language> findByCode(String code);
    Page<Language> search(List<String> codes, List<String> names, Boolean isActive, Pageable pageable);
}
