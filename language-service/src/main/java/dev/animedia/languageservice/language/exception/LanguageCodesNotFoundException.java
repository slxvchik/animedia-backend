package dev.animedia.languageservice.language.exception;

import dev.animedia.languageservice.app.exception.AppException;
import dev.animedia.languageservice.language.LanguageConstants;
import org.springframework.http.HttpStatus;

public class LanguageCodesNotFoundException extends AppException {

    public LanguageCodesNotFoundException() {
        super(HttpStatus.NOT_FOUND, LanguageConstants.LANGUAGE_CODES_NOT_FOUND_MESSAGE);
    }
    
}
