package dev.animedia.languageservice.domain.exception;

public class InvalidLanguageCodeException extends AppException {
    public InvalidLanguageCodeException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "INVALID_LANGUAGE_CODE");
    }
}
