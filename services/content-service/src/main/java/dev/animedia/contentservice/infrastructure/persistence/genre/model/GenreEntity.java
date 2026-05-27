package dev.animedia.contentservice.infrastructure.persistence.genre.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(
    name = "genre",
    indexes = {
        @Index(name = "idx_genre_alias", columnList = "alias")
    }
)
public class GenreEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 128, nullable = false, updatable = false, unique = true)
    private String alias;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Column(name = "active")
    private Boolean active = false;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "genreEntity", orphanRemoval = true)
    private Set<GenreTranslationEntity> translations = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        // delete translations
        this.translations.removeIf(existing -> !newGenreTranslationEntitySet.contains(existing));
        // save new && update old translations
        for (GenreTranslationEntity newGte : newGenreTranslationEntitySet) {
            if (newGte.getId() == null) {
                this.translations.add(newGte);
            } else {
                this.translations.stream()
                    .filter(existing -> existing.getId().equals(newGte.getId()))
                    .findFirst()
                    .ifPresent(existing -> {
                        existing.setName(newGte.getName());
                        existing.setDescription(newGte.getDescription());
                    });
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof GenreEntity that)) return false;
        return alias.equals(that.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(alias);
    }
}