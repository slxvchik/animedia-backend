package dev.animedia.languageservice.config;

import dev.animedia.languageservice.exception.AppException;
import dev.animedia.languageservice.exception.AppExceptionMessageService;
import io.grpc.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class GlobalExceptionInterceptor implements ServerInterceptor {

	private final AppExceptionMessageService appExceptionMessageService;

	public GlobalExceptionInterceptor(AppExceptionMessageService appExceptionMessageService) {
		this.appExceptionMessageService = appExceptionMessageService;
	}

	@Override
	public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
		ServerCall<ReqT, RespT> serverCall,
		Metadata headers,
		ServerCallHandler<ReqT, RespT> next
	) {
		ServerCall.Listener<ReqT> delegate;
		try {
			delegate = next.startCall(serverCall, headers);
		} catch(Exception ex) {
			return handleInterceptorException(ex, serverCall);
		}
		return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(delegate) {
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

	private <ReqT, RespT> ServerCall.Listener<ReqT> handleInterceptorException(
		Throwable ex,
		ServerCall<ReqT, RespT> serverCall
	) {
		if (ex instanceof AppException appException) {
			List<String> errorMessages = appExceptionMessageService.getExceptionMessage(appException.getCodes());
			Metadata metadata = new Metadata();
			Metadata.Key<byte[]> errorKey = Metadata.Key.of("error-messages-bin", Metadata.BINARY_BYTE_MARSHALLER);
			byte[] errorBytes = String.join("|", errorMessages).getBytes(StandardCharsets.UTF_8);
			metadata.put(
				errorKey,
				errorBytes
			);

			serverCall.close(
				appException.getGrpcStatus().toStatus().withDescription(ex.getMessage()),
				metadata
			);
		} else {
			serverCall.close(Status.INTERNAL
				.withDescription("Here some troubles..."), new Metadata()
			);
		}
		return new ServerCall.Listener<>() {};
	}
}
