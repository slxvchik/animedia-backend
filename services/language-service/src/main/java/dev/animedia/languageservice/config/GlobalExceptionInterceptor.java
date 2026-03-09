package dev.animedia.languageservice.config;

import java.nio.charset.StandardCharsets;
import java.util.List;

import dev.animedia.languageservice.exception.AppException;
import dev.animedia.languageservice.exception.AppExceptionMessageService;
import dev.animedia.languageservice.mapper.AppExceptionStatusMapper;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;

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
			List<String> errorMessages = appExceptionMessageService.getExceptionMessage(appException.getCodes());
			Metadata metadata = new Metadata();
			Metadata.Key<byte[]> errorKey = Metadata.Key.of("error-messages-bin", Metadata.BINARY_BYTE_MARSHALLER);
			byte[] errorBytes = String.join("|", errorMessages).getBytes(StandardCharsets.UTF_8);
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
