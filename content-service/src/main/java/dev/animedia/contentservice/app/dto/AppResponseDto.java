package dev.animedia.contentservice.app.dto;

public class AppResponseDto<T> {
    private final ResponseStatus status;
    private final T data;
    private final String error;

    private enum ResponseStatus {
        SUCCESS, ERROR
    }

    private AppResponseDto(ResponseStatus status, T data, String error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> AppResponseDto<T> success(T data) {
        return new AppResponseDto<>(ResponseStatus.SUCCESS, data, null);
    }

    public static <T> AppResponseDto<T> error(String error) {
        return new AppResponseDto<>(ResponseStatus.ERROR, null, error);
    }

    public ResponseStatus getStatus() {
        return status;
    }
    
    public T getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}
