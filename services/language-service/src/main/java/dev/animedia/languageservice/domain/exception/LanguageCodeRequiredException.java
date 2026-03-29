package dev.animedia.languageservice.domain.exception;

public class LanguageCodeRequiredException extends AppException {
    public LanguageCodeRequiredException() {
        super(AppExceptionStatus.INVALID_ARGUMENT, "LANGUAGE_CODE_REQUIRED");
    }
}
