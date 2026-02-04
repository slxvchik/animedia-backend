package dev.animedia.contentservice.language.exception;

import dev.animedia.contentservice.language.LanguageConstants;
import org.springframework.http.HttpStatus;

import dev.animedia.contentservice.app.exception.AppException;

public class LanguageCodeNotFoundException extends AppException {

    public LanguageCodeNotFoundException() {
        super(HttpStatus.NOT_FOUND, LanguageConstants.LANGUAGE_CODE_NOT_FOUND_MESSAGE);
    }
    
}
