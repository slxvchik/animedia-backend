package dev.animedia.languageservice.app.exception;

import io.grpc.Metadata;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.server.exception.GrpcExceptionHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AppExceptionHandler {

    private final AppExceptionMessageService appExceptionMessageService;

    private static final Logger LOGGER = Logger.getLogger(AppExceptionHandler.class.getName());

    @Autowired
    public AppExceptionHandler(AppExceptionMessageService appExceptionMessageService) {
        this.appExceptionMessageService = appExceptionMessageService;
    }

	@Bean
	GrpcExceptionHandler grpcExceptionHandler() {
		return ex -> {
			if (ex instanceof AppException appException) {
				LOGGER.log(Level.SEVERE, appException.getMessage());
				List<String> errorMessages = appExceptionMessageService.getExceptionMessage(appException.getCodes());
				Metadata metadata = new Metadata();
				metadata.put(Metadata.Key.of("ERROR_MESSAGES", Metadata.ASCII_STRING_MARSHALLER), String.join(",", errorMessages));
				return appException
					.getGrpcStatus()
					.toStatus()
					.withDescription(ex.getMessage())
					.asException(metadata);
			}
			LOGGER.log(Level.SEVERE, ex.getMessage());
			return Status.INTERNAL.withDescription("Here some troubles...").asException();
		};
	}

//    @GrpcExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<AppResponseDto<List<String>>> handleValidationError(MethodArgumentNotValidException exception) throws IOException {
//
//        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
//
//        List<FieldError> fieldErrors = exception.getFieldErrors();
//
//        List<String> errorCodes = fieldErrors.stream()
//            .map(FieldError::getDefaultMessage)
//            .toList();
//
//        try {
//            List<String> errorMessages = appExceptionMessageService.getExceptionMessage(errorCodes, languageCode);
//            AppResponseDto<List<String>> response = AppResponseDto.error(errorMessages);
//
//            Status status = Status.newBuilder()
//                .setCode(Code.INVALID_ARGUMENT_VALUE)
//                .setMessage(response)
//                .build();
//
//            return StatusProto.toStatusRuntimeException(status);
//
//        } catch (AppErrorTranslationException appErrorTranslationException) {
//
//            AppResponseDto<List<String>> response = AppResponseDto.error(
//                appErrorTranslationException.getMessage(languageCode)
//            );
//
//            return ResponseEntity
//                .status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(response);
//        }
//    }
//
//    @GrpcExceptionHandler(AppException.class)
//    public ResponseEntity<AppResponseDto<List<String>>> handleAppError(AppException exception) throws IOException {
//
//        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
//
//        List<String> errorMessage = appExceptionMessageService.getExceptionMessage(exception.getCode(), languageCode);
//
//        AppResponseDto<List<String>> response = AppResponseDto.error(errorMessage);
//
//        return ResponseEntity
//            .status(exception.getHttpStatus())
//            .body(response);
//    }
//
//    @GrpcExceptionHandler(Exception.class)
//    public ResponseEntity<AppResponseDto<List<String>>> handleException(Exception exception) {
//
//        LOGGER.log(Level.SEVERE, "handleException: {0}", exception.getMessage());
//
//        String languageCode = LocaleLanguageContext.getLocaleLanguageCode();
//
//        List<String> errorMessage;
//        try {
//            if (isClientError(exception)) {
//                errorMessage = appExceptionMessageService.getExceptionMessage("CLIENT_ERROR", languageCode);
//                return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(AppResponseDto.error(errorMessage));
//            } else {
//                errorMessage = appExceptionMessageService.getExceptionMessage("SERVER_ERROR", languageCode);
//            }
//        } catch (Exception ex) {
//            LOGGER.log(Level.SEVERE, "handleException translate exception: {0}", ex.getMessage());
//            errorMessage = List.of("An unknown error has occurred");
//        }
//        return ResponseEntity
//            .status(HttpStatus.INTERNAL_SERVER_ERROR)
//            .body(AppResponseDto.error(errorMessage));
//    }

}
