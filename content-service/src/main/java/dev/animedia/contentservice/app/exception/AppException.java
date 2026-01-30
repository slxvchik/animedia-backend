package dev.animedia.contentservice.app.exception;

import org.springframework.http.HttpStatus;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AppException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final String code;
    private static final Logger LOGGER = Logger.getLogger(AppException.class.getName());

    protected AppException(HttpStatus httpStatus, String code) {
        LOGGER.log(Level.WARNING, "App exception: {0}", String.format("Http status code: %s; code: %s;",  httpStatus.toString(), code));
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
