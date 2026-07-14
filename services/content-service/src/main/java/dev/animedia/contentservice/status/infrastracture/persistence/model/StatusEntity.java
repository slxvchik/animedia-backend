package dev.animedia.contentservice.status.infrastracture.persistence.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(
    name = "statusId",
    indexes = {
        @Index(name = "idx_status_alias", columnList = "alias")
    }
)
public class StatusEntity {
    @Id
    private UUID id;

    @PrePersist
    private void generateId() {
        if (this.id == null) {
            this.id = UUID.randomUUID();
        }
    }

    @Column(length = 512, unique = true, nullable = false, updatable = false)
    private String alias;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder = 0;

    @Column(name = "active", nullable = false)
    private Boolean active = false;

    @OneToMany(
        fetch = FetchType.LAZY,
        mappedBy = "statusEntity",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    Set<StatusTranslationEntity> translations = new HashSet<>();

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

    public Set<StatusTranslationEntity> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<StatusTranslationEntity> translationSet) {
        this.translations = translationSet;
    }

    public void syncTranslationSet(Set<StatusTranslationEntity> newStatusTranslationSet) {
        if (newStatusTranslationSet == null || newStatusTranslationSet.isEmpty()) {
            this.translations.clear();
            return;
        }

        // delete translations
        this.translations.removeIf(existing -> !newStatusTranslationSet.contains(existing));

        // save new & update old translations
        for (StatusTranslationEntity newSte : newStatusTranslationSet) {
            this.translations.stream()
                .filter(existing -> existing.equals(newSte))
                .findFirst()
                .ifPresentOrElse(
                    existing -> existing.setName(newSte.getName()),
                    () -> {
                        newSte.setId(null);
                        newSte.setStatusEntity(this);
                        this.translations.add(newSte);
                    }
                );
        }
    }
}