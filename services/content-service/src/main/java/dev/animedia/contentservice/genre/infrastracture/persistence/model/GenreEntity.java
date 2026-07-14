package dev.animedia.contentservice.genre.infrastracture.persistence.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
    name = "genre",
    indexes = {
        @Index(name = "idx_genre_alias", columnList = "alias")
    }
)
public class GenreEntity {
    @Id
    private UUID id;

    @PrePersist
    private void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @Column(length = 128, nullable = false, updatable = false, unique = true)
    private String alias;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "genreEntity",
        orphanRemoval = true
    )
    private Set<GenreTranslationEntity> translations = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<GenreTranslationEntity> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<GenreTranslationEntity> translationSet) {
        this.translations = translationSet;
    }

    public void syncTranslationSet(Set<GenreTranslationEntity> newGenreTranslationEntitySet) {
        if (newGenreTranslationEntitySet == null || newGenreTranslationEntitySet.isEmpty()) {
            this.translations.clear();
            return;
        }

        // delete translations
        this.translations.removeIf(existing -> !newGenreTranslationEntitySet.contains(existing));

        // save new && update old translations
        for (GenreTranslationEntity newGte : newGenreTranslationEntitySet) {
            this.translations.stream()
                .filter(existing -> existing.equals(newGte))
                .findFirst()
                .ifPresentOrElse(
                    existing -> existing.setName(newGte.getName()),
                    () -> {
                        newGte.setId(null);
                        newGte.setGenreEntity(this);
                        this.translations.add(newGte);
                    }
                );
        }
    }
}