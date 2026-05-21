package dev.animedia.contentservice.infrastructure.persistence.status.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
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

    @Column(length = 512, unique = true, nullable = false, updatable = false)
    private String alias;

    @Column(name = "sort_order")
    private Integer sortOrder = 0;

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "statusEntity",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
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

    public void syncTranslationSet(Set<StatusTranslationEntity> newStatusTranslationSet) {
        // delete translations
        this.translationSet.removeIf(existing -> !newStatusTranslationSet.contains(existing));

        // save new & update old translations
        for (StatusTranslationEntity newTranslationEntity : newStatusTranslationSet) {
            if (newTranslationEntity.getId() == null) {
                this.translationSet.add(newTranslationEntity);
            } else {
                this.translationSet.stream()
                    .filter(existing -> existing.getId().equals(newTranslationEntity.getId()))
                    .findFirst()
                    .ifPresent(existing -> existing.setName(newTranslationEntity.getName()));
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StatusEntity that)) return false;
        return alias.equals(that.alias);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(alias);
    }
}