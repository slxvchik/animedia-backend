package dev.animedia.contentservice.app.exception;

import org.springframework.http.HttpStatus;

import java.util.Map;

public abstract class AppException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String code;
    private final Map<String, String> params;

    public AppException(HttpStatus httpStatus, String code, Map<String, String> params) {
        super("Code error: " + code);
        this.httpStatus = httpStatus;
        this.code = code;
        this.params = params;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getCode() {
        return code;
    }

    public Map<String, String> getParams() {
        return params;
    }
}
