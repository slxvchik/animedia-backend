package dev.animedia.contentservice.app.exception;

import org.springframework.http.HttpStatus;

public class ErrorLocaleException extends RuntimeException {
    private final HttpStatus status = HttpStatus.BAD_REQUEST;
    private final String error = "Bad request";
    public ErrorLocaleException(String message) {
        super(message);
    }
}
