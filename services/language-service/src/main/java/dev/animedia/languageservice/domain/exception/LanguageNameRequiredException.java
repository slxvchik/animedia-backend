package dev.animedia.languageservice.domain.exception;

public class LanguageNameRequiredException extends AppException {
    public LanguageNameRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "LANGUAGE_NAME_REQUIRED");
    }
}
