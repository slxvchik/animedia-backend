package dev.animedia.languageservice.domain.model;

import dev.animedia.languageservice.domain.exception.InvalidLanguageCodeException;
import dev.animedia.languageservice.domain.exception.LanguageCodeRequiredException;
import dev.animedia.languageservice.domain.exception.LanguageNameRequiredException;

import java.util.regex.Pattern;

public class Language {
    private final String code;
    private String name;
    private boolean isActive;
    private boolean isDefault;
    private int sortOrder;
    private String flagEmoji;
    private static final Pattern CODE_PATTERN = Pattern.compile("^[a-z]{2}$");

    public Language(String code, String name, boolean isActive, boolean isDefault, int sortOrder, String flagEmoji) {
        validateCode(code);
        validateName(name);
        this.code = code;
        this.name = name;
        this.isActive = isActive;
        this.isDefault = isDefault;
        setSortOrder(sortOrder);
        this.flagEmoji = flagEmoji;
    }

    private void validateCode(String code) {
        if (code == null || code.isBlank()) throw new LanguageCodeRequiredException();
        if (!CODE_PATTERN.matcher(code).hasMatch()) throw new InvalidLanguageCodeException();
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) throw new LanguageNameRequiredException();
    }

    public void update(String name, boolean isActive, boolean isDefault, int sortOrder, String flagEmoji) {
        validateName(name);
        this.name = name;
        this.isActive = isActive;
        this.isDefault = isDefault;
        setSortOrder(sortOrder);
        this.flagEmoji = flagEmoji;
    }

    private void setSortOrder(int sortOrder) {
        this.sortOrder = Math.max(sortOrder, 0);
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsDefault() {
        return isDefault;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public String getFlagEmoji() {
        return flagEmoji;
    }
}
