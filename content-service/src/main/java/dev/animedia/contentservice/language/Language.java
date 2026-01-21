package dev.animedia.contentservice.language;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Language {
    @Id
    @Column(columnDefinition = "CHAR(2)", unique = true, nullable = false)
    private Character code;
    @Column(length = 40, unique = true, nullable = false)
    private String name;

    public Character getCode() {
        return code;
    }

    public void setCode(Character code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
