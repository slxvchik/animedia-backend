package dev.animedia.contentservice.app.exception;

import org.springframework.http.HttpStatus;

public abstract class AppException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String code;

    public AppException(HttpStatus httpStatus, String code) {
        super("Code error: " + code);
        this.httpStatus = httpStatus;
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }
}
