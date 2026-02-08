package dev.animedia.contentservice.contentstatus.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "content_status",
    indexes = {
        @Index(name = "idx_content_status_alias", columnList = "alias")
    }
)
public class ContentStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 512, unique = true, nullable = false)
    private String alias;

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
}
