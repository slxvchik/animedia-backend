package dev.animedia.contentservice.contentstatus.core;

import dev.animedia.contentservice.contentstatus.translation.ContentStatusTranslation;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "content_status")
public class ContentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, unique = true, nullable = false)
    private String alias;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ContentStatusTranslation> translations = new HashSet<>();

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

    public Set<ContentStatusTranslation> getTranslations() {
        return translations;
    }

    public void setTranslations(Set<ContentStatusTranslation> translations) {
        this.translations = translations;
    }
}
