package dev.animedia.contentservice.presentation.grpc.config;

import dev.animedia.contentservice.domain.shared.exception.AppException;
import dev.animedia.contentservice.presentation.exception.AppExceptionMessageService;
import dev.animedia.contentservice.presentation.exception.AppExceptionStatusMapper;
import io.grpc.*;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GlobalExceptionInterceptor implements ServerInterceptor {
	private final AppExceptionMessageService appExceptionMessageService;
	private final AppExceptionStatusMapper appExceptionStatusMapper;

	private static final Logger LOGGER = Logger.getLogger(GlobalExceptionInterceptor.class.getName());

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

			LOGGER.log(Level.WARNING, appException.getCode());

			String languageCode = LanguageInterceptor.getLanguageCode();
			String errorMessage = appExceptionMessageService.getExceptionMessage(
				appException.getCode(),
				languageCode,
				appException.getArgs()
			);

			Metadata metadata = new Metadata();
			Metadata.Key<byte[]> key = Metadata.Key.of("error-message-bin", Metadata.BINARY_BYTE_MARSHALLER);
			byte[] value = errorMessage.getBytes(StandardCharsets.UTF_8);
			metadata.put(key, value);

			call.close(
				appExceptionStatusMapper.toGrpcCode(appException.getStatus()).toStatus().withDescription(ex.getMessage()),
				metadata
			);
		} else {
			LOGGER.log(Level.SEVERE, ex.getMessage());
			call.close(Status.INTERNAL.withDescription("An error has occurred, please refresh the page or try again later"), new Metadata());
		}
		return new ServerCall.Listener<>() {};
	}
}