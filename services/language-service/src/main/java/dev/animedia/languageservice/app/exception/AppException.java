package dev.animedia.languageservice.app.exception;

import io.grpc.Status.Code;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AppException extends RuntimeException {

    private final Code grpcStatus;
    private final String code;
    private static final Logger LOGGER = Logger.getLogger(AppException.class.getName());

    protected AppException(Code grpcStatus, String code) {
        LOGGER.log(Level.WARNING, "App exception: {0}", String.format("Grpc status code: %s; code: %s;",  grpcStatus.name(), code));
        this.grpcStatus = grpcStatus;
        this.code = code;
    }

    public Code getGrpcStatus() {
        return grpcStatus;
    }

    public String getCode() {
        return code;
    }
}
