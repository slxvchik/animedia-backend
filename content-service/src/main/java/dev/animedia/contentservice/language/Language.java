package dev.animedia.contentservice.language;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Language {
    @Id
    @Column(columnDefinition = "VARCHAR(2)", unique = true, nullable = false)
    private String code;

    @Column(length = 128, unique = true, nullable = false)
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
