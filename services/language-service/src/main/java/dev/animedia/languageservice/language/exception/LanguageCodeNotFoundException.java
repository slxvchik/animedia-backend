package dev.animedia.languageservice.language.exception;

import dev.animedia.languageservice.language.LanguageConstants;
import io.grpc.Status;

import dev.animedia.languageservice.app.exception.AppException;

public class LanguageCodeNotFoundException extends AppException {
    public LanguageCodeNotFoundException() {
        super(Status.Code.NOT_FOUND, LanguageConstants.LANGUAGE_CODE_NOT_FOUND_MESSAGE);
    }
}
