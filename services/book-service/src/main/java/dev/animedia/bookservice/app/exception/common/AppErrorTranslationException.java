package dev.animedia.bookservice.app.exception.common;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppErrorTranslationException extends RuntimeException {

    private static final Logger LOGGER = Logger.getLogger(AppErrorTranslationException.class.getName());

    public AppErrorTranslationException(String message) {
        LOGGER.log(Level.SEVERE, "App error translation exception: {0}", message);
        super(message);
    }

    public String getMessage(String lang) {
        switch (lang) {
            case "ru" -> {
                return "Произошла внутрення ошибка при переводе ошибки";
            }
            default -> {
                return "An internal error occurred while translating the error";
            }
        }
    }
}
