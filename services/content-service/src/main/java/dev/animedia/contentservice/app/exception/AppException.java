package dev.animedia.contentservice.app.exception;

import io.grpc.Status.Code;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppException extends RuntimeException {

    private final Code grpcStatus;
    private final List<String> codes;
    private static final Logger LOGGER = Logger.getLogger(AppException.class.getName());

    public AppException(Code grpcStatus, List<String> codes) {
        LOGGER.log(Level.WARNING, "App exception: {0}", String.format("Grpc status code: %s; codes: %s;",  grpcStatus.name(), codes));
        this.grpcStatus = grpcStatus;
        this.codes = codes;
    }

    public AppException(Code grpcStatus, String codes) {
        this(grpcStatus, List.of(codes));
    }

    public AppException(List<String> codes) {
        LOGGER.log(Level.WARNING, "App exception: {0}", String.format("Codes: %s;", codes));
        this.grpcStatus = Code.INTERNAL;
        this.codes = codes;
    }

    public AppException(String code) {
        this(List.of(code));
    }

    public Code getGrpcStatus() {
        return grpcStatus;
    }

    public List<String> getCodes() {
        return codes;
    }
}
