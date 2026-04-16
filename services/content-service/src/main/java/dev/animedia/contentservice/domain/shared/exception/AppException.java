package dev.animedia.contentservice.domain.shared.exception;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AppException extends RuntimeException {
    private final AppExceptionStatus status;
    private final String code;
    private static final Logger LOGGER = Logger.getLogger(AppException.class.getName());

    public AppException(AppExceptionStatus status, String code) {
        LOGGER.log(Level.WARNING, "App exception: {0}", String.format("Code: %s;", code));
        this.status = status;
        this.code = code;
    }

    public AppExceptionStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }
}

