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

    @Column(name = "active")
    private Boolean active = false;

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "statusEntity",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    Set<StatusTranslationEntity> translations = new HashSet<>();

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

    public Set<StatusTranslationEntity> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<StatusTranslationEntity> translationSet) {
        this.translations = translationSet;
    }

    public void syncTranslationSet(Set<StatusTranslationEntity> newStatusTranslationSet) {
        // delete translations
        this.translations.removeIf(existing -> !newStatusTranslationSet.contains(existing));

        // save new & update old translations
        for (StatusTranslationEntity newTranslationEntity : newStatusTranslationSet) {
            if (newTranslationEntity.getId() == null) {
                this.translations.add(newTranslationEntity);
            } else {
                this.translations.stream()
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