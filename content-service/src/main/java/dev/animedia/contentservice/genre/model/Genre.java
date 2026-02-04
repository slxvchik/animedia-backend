package dev.animedia.contentservice.genre.model;

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

    @Column(nullable = false)
    private Long sort = 0L;

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

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

}
