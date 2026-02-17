package dev.animedia.languageservice.language.exception;

import dev.animedia.languageservice.language.LanguageConstants;
import org.springframework.http.HttpStatus;

import dev.animedia.languageservice.app.exception.AppException;

public class LanguageCodeNotFoundException extends AppException {

    public LanguageCodeNotFoundException() {
        super(HttpStatus.NOT_FOUND, LanguageConstants.LANGUAGE_CODE_NOT_FOUND_MESSAGE);
    }
    
}
