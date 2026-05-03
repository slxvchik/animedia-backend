package dev.animedia.languageservice.infrastructure.persistence.repository;

import dev.animedia.languageservice.domain.model.Language;
import dev.animedia.languageservice.domain.repository.LanguageCommandRepository;
import dev.animedia.languageservice.infrastructure.persistence.mapper.LanguagePersistenceMapper;
import dev.animedia.languageservice.infrastructure.persistence.model.LanguageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageCommandRepositoryAdapter implements LanguageCommandRepository {
    private final JpaLanguageRepository jpaLanguageRepository;
    private final LanguagePersistenceMapper languagePersistenceMapper;

    @Autowired
    public LanguageCommandRepositoryAdapter(JpaLanguageRepository jpaLanguageRepository, LanguagePersistenceMapper languagePersistenceMapper) {
        this.jpaLanguageRepository = jpaLanguageRepository;
        this.languagePersistenceMapper = languagePersistenceMapper;
    }

    @Override
    public Language create(Language language) {
        LanguageEntity entity = languagePersistenceMapper.toEntity(language);
        LanguageEntity saved = jpaLanguageRepository.save(entity);
        return languagePersistenceMapper.toDomain(saved);
    }

    @Override
    public Language update(Language language) {
        LanguageEntity entity = languagePersistenceMapper.toEntity(language);
        LanguageEntity updated = jpaLanguageRepository.save(entity);
        return languagePersistenceMapper.toDomain(updated);
    }

    @Override
    public void delete(String code) {
        jpaLanguageRepository.deleteById(code);
    }
}
