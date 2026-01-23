package dev.animedia.contentservice.genre.core;

import jakarta.persistence.*;

@Entity
@Table(
    indexes = {
        @Index(name = "idx_genre_alias", columnList = "alias")
    }
)
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 128, nullable = false, unique = true)
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
