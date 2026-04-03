package dev.animedia.contentservice.old.app.config;

import dev.animedia.contentservice.old.app.exception.AppException;
import dev.animedia.contentservice.old.app.exception.AppExceptionMessageService;
import dev.animedia.contentservice.old.app.exception.AppExceptionStatusMapper;
import io.grpc.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

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
		ServerCall<ReqT, RespT> call,
		Metadata headers,
		ServerCallHandler<ReqT, RespT> next
	) {
		ServerCall.Listener<ReqT> delegate;
		try {
			delegate = next.startCall(call, headers);
		} catch (Exception ex) {
			return handleInterceptorException(ex, call);
		}
		return new ForwardingServerCallListener.SimpleForwardingServerCallListener<>(delegate) {
			@Override
			public void onHalfClose() {
				try {
					super.onHalfClose();
				} catch (Exception ex) {
					handleInterceptorException(ex, call);
				}
			}
		};
	}

	private <ReqT, RespT> ServerCall.Listener<ReqT> handleInterceptorException(
		Throwable ex,
		ServerCall<ReqT, RespT> call
	) {
		if (ex instanceof AppException appException) {
			List<String> errorMessages = appExceptionMessageService.getExceptionMessage(appException.getCodes());

			Metadata metadata = new Metadata();
			Metadata.Key<byte[]> key = Metadata.Key.of("error-messages-bin", Metadata.BINARY_BYTE_MARSHALLER);
			byte[] value = String.join("|", errorMessages).getBytes(StandardCharsets.UTF_8);
			metadata.put(key, value);

			call.close(
				appExceptionStatusMapper.toGrpcCode(appException.getStatus()).toStatus().withDescription(ex.getMessage()),
				metadata
			);
		} else {
			call.close(Status.INTERNAL.withDescription("Ooops... Here some troubles..."), new Metadata());
		}
		return new ServerCall.Listener<>() {};
	}

}
