package dev.animedia.languageservice.infrastructure.persistence.repository;

import dev.animedia.languageservice.domain.model.Language;
import dev.animedia.languageservice.domain.repository.LanguageCommandRepository;
import dev.animedia.languageservice.infrastructure.persistence.mapper.LanguagePersistenceMapper;
import dev.animedia.languageservice.infrastructure.persistence.model.LanguageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageCommandRepositoryAdapter implements LanguageCommandRepository {
    private final LanguageJpaRepository languageJpaRepository;
    private final LanguagePersistenceMapper languagePersistenceMapper;

    @Autowired
    public LanguageCommandRepositoryAdapter(LanguageJpaRepository languageJpaRepository, LanguagePersistenceMapper languagePersistenceMapper) {
        this.languageJpaRepository = languageJpaRepository;
        this.languagePersistenceMapper = languagePersistenceMapper;
    }

    @Override
    public Language create(Language language) {
        LanguageEntity entity = languagePersistenceMapper.toEntity(language);
        LanguageEntity saved = languageJpaRepository.save(entity);
        return languagePersistenceMapper.toDomain(saved);
    }

    @Override
    public Language update(Language language) {
        LanguageEntity entity = languagePersistenceMapper.toEntity(language);
        LanguageEntity updated = languageJpaRepository.save(entity);
        return languagePersistenceMapper.toDomain(updated);
    }

    @Override
    public void delete(String code) {
        languageJpaRepository.deleteById(code);
    }
}
