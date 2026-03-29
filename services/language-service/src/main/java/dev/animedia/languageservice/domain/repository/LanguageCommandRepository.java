package dev.animedia.languageservice.domain.repository;

import dev.animedia.languageservice.domain.model.Language;

public interface LanguageCommandRepository {
    Language create(Language language);
    Language update(Language language);
    void delete(String code);
}
