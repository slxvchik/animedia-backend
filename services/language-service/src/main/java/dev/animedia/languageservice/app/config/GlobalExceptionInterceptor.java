package dev.animedia.languageservice.app.config;

import dev.animedia.languageservice.app.exception.AppException;
import dev.animedia.languageservice.app.exception.AppExceptionMessageService;
import io.grpc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GlobalExceptionInterceptor implements ServerInterceptor {

	private final AppExceptionMessageService appExceptionMessageService;

	@Autowired
	public GlobalExceptionInterceptor(AppExceptionMessageService appExceptionMessageService) {
		this.appExceptionMessageService = appExceptionMessageService;
	}

	@Override
	public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata headers,
		ServerCallHandler<ReqT, RespT> next) {
		ServerCall.Listener<ReqT> delegate = null;
		try {
			delegate = next.startCall(serverCall, headers);
		} catch(Exception ex) {
			handleInterceptorException(ex, serverCall);
			return
		}
		return new ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT>(delegate) {
			@Override
			public void onHalfClose() {
				try {
					super.onHalfClose();
				} catch (Exception ex) {
					handleInterceptorException(ex, serverCall);
				}
			}
		};
	}

	private static <ReqT, RespT> void handleInterceptorException(Exception ex, ServerCall<ReqT, RespT> serverCall) {
		if (ex instanceof AppException appException) {
			List<String> errorMessages = appExceptionMessageService.getExceptionMessage(appException.getCodes());
			Metadata metadata = new Metadata();
			metadata.put(Metadata.Key.of("ERROR_MESSAGES", Metadata.ASCII_STRING_MARSHALLER), String.join(",", errorMessages));

			serverCall.close(appException.getGrpcStatus().toStatus()
				.withDescription(ex.getMessage()),
				metadata
			);
		} else {
			serverCall.close(Status.INTERNAL
				.withDescription("Here some troubles..."), new Metadata()
			);
		}
	}
}
