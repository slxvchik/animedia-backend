package dev.animedia.languageservice.domain.model;

import dev.animedia.languageservice.domain.exception.InvalidLanguageCodeException;
import dev.animedia.languageservice.domain.exception.LanguageCodeRequiredException;
import dev.animedia.languageservice.domain.exception.LanguageNameRequiredException;

import java.util.regex.Pattern;

public class Language {
    private final String code;
    private String name;
    private Boolean isActive;
    private Boolean isDefault;
    private Integer sortOrder;
    private String flagEmoji;
    private final static Pattern CODE_PATTERN = Pattern.compile("^[a-z]{2}$");

    public Language(String code, String name, Boolean isActive, Boolean isDefault, Integer sortOrder, String flagEmoji) {
        validateCode(code);
        validateName(name);
        this.code = code;
        this.name = name;
        setIsActive(isActive);
        setIsDefault(isDefault);
        setSortOrder(sortOrder);
        this.flagEmoji = flagEmoji;
    }

    private void validateCode(String code) {
        if (code == null || code.isBlank()) throw new LanguageCodeRequiredException();
        if (!CODE_PATTERN.matcher(code).matches()) throw new InvalidLanguageCodeException();
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) throw new LanguageNameRequiredException();
    }

    public void update(String name, Boolean isActive, Boolean isDefault, Integer sortOrder, String flagEmoji) {
        validateName(name);
        this.name = name;
        setIsActive(isActive);
        setIsDefault(isDefault);
        setSortOrder(sortOrder);
        this.flagEmoji = flagEmoji;
    }

    private void setIsActive(Boolean isActive) {
        this.isActive = isActive != null ? isActive : false;
    }

    private void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault != null ? isDefault : false;
    }

    private void setSortOrder(Integer sortOrder) {
        this.sortOrder = (sortOrder != null && sortOrder > 0) ? sortOrder : 0;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public Integer getSortOrder() {
        return sortOrder;
    }

    public String getFlagEmoji() {
        return flagEmoji;
    }
}
