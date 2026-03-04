package dev.animedia.languageservice.exception;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppException extends RuntimeException {

    private final AppExceptionStatus status;
    private final List<String> codes;
    private static final Logger LOGGER = Logger.getLogger(AppException.class.getName());

    public AppException(AppExceptionStatus status, List<String> codes) {
        LOGGER.log(Level.WARNING, "App exception: {0}", String.format("Grpc status code: %s; codes: %s;",  status.name(), codes));
        this.status = status;
        this.codes = codes;
    }

    public AppException(AppExceptionStatus status, String codes) {
        this(status, List.of(codes));
    }

    public AppException(List<String> codes) {
        LOGGER.log(Level.WARNING, "App exception: {0}", String.format("Codes: %s;", codes));
        this.status = AppExceptionStatus.INTERNAL_ERROR;
        this.codes = codes;
    }

    public AppException(String code) {
        this(List.of(code));
    }

    public AppExceptionStatus getStatus() {
        return status;
    }

    public List<String> getCodes() {
        return codes;
    }
}
