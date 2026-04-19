package dev.animedia.contentservice.infrastructure.status.persistence.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
    name = "status",
    indexes = {
        @Index(name = "idx_status_alias", columnList = "alias")
    }
)
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 512, unique = true, nullable = false)
    private String alias;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "statusEntity")
    Set<StatusTranslationEntity> translationSet = new HashSet<>();

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

    public Set<StatusTranslationEntity> getTranslationSet() {
        return translationSet;
    }

    public void setTranslationSet(Set<StatusTranslationEntity> translationSet) {
        this.translationSet = translationSet;
    }
}