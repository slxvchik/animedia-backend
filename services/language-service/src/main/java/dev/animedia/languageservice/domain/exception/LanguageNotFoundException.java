package dev.animedia.languageservice.domain.exception;

public class LanguageNotFoundException extends AppException {
    public LanguageNotFoundException() {
        super(AppExceptionStatus.NOT_FOUND, "LANGUAGE_NOT_FOUND");
    }
}
