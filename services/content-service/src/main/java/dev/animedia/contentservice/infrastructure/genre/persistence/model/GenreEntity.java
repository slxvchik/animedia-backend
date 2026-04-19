package dev.animedia.contentservice.infrastructure.genre.persistence.model;

import jakarta.persistence.*;

import java.util.HashSet;
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

    @Column(length = 128, nullable = false, unique = true)
    private String alias;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "")
    private Set<GenreTranslationEntity> translationSet = new HashSet<>();

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

    public Set<GenreTranslationEntity> getTranslationSet() {
        return translationSet;
    }

    public void setTranslationSet(Set<GenreTranslationEntity> translationSet) {
        this.translationSet = translationSet;
    }
}
