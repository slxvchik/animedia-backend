package dev.animedia.languageservice.app.exception;

import io.grpc.Metadata;
import io.grpc.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.grpc.server.exception.GrpcExceptionHandler;
import org.springframework.stereotype.Component;

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
}
