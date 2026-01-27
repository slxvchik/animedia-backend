package dev.animedia.contentservice.app.exception;

public class ErrorLocaleException extends RuntimeException {
    public ErrorLocaleException(String message) {
        super(message);
    }

    public String getMessage(String lang) {
        switch (lang) {
            case "ru" -> {
                return "Произошла внутрення ошибка при переводе!";
            }
            default -> {
                return "An internal error occurred during the translation!";
            }
        }
    }
}
