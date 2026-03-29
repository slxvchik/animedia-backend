package dev.animedia.languageservice.infrastructure.persistence.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class LanguageEntity {
    @Id
    @Column(columnDefinition = "VARCHAR(2)", unique = true, nullable = false)
    private String code;

    @Column(length = 128, unique = true, nullable = false)
    private String name;

    @Column(nullable = false, name = "is_active")
    private Boolean isActive = false;

    @Column(nullable = false, name = "is_default")
    private Boolean isDefault = false;

    @Column(nullable = false, name = "sort_order")
    private Integer sortOrder = 0;

    @Column(name = "flag_emoji")
    private String flagEmoji;

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

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean aDefault) {
        isDefault = aDefault;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    public String getFlagEmoji() {
        return flagEmoji;
    }

    public void setFlagEmoji(String flagEmoji) {
        this.flagEmoji = flagEmoji;
    }
}
