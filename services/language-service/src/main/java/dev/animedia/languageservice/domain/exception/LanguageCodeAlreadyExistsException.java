package dev.animedia.languageservice.domain.exception;

public class LanguageCodeAlreadyExistsException extends AppException {
    public LanguageCodeAlreadyExistsException() {
        super(AppExceptionStatus.ALREADY_EXISTS, "LANGUAGE_CODE_ALREADY_EXISTS");
    }
}
