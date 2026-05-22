package dev.animedia.languageservice.presentation.exception;

import dev.animedia.languageservice.domain.exception.AppException;
import dev.animedia.languageservice.presentation.grpc.mapper.AppExceptionStatusMapper;
import io.grpc.*;

import java.nio.charset.StandardCharsets;

public class GlobalExceptionInterceptor implements ServerInterceptor {

	private final AppExceptionMessageService appExceptionMessageService;
	private final AppExceptionStatusMapper appExceptionStatusMapper;

	public GlobalExceptionInterceptor(
		AppExceptionMessageService appExceptionMessageService,
		AppExceptionStatusMapper appExceptionStatusMapper
	) {
		this.appExceptionMessageService = appExceptionMessageService;
		this.appExceptionStatusMapper = appExceptionStatusMapper;
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
			String errorMessage = appExceptionMessageService.getExceptionMessage(appException.getCode());
			Metadata metadata = new Metadata();
			Metadata.Key<byte[]> errorKey = Metadata.Key.of("error-messages-bin", Metadata.BINARY_BYTE_MARSHALLER);
			byte[] errorBytes = errorMessage.getBytes(StandardCharsets.UTF_8);
			metadata.put(
				errorKey,
				errorBytes
			);

			serverCall.close(
				appExceptionStatusMapper.toGrpcCode(
					appException.getStatus()
				)
				.toStatus()
				.withDescription(ex.getMessage()),
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
