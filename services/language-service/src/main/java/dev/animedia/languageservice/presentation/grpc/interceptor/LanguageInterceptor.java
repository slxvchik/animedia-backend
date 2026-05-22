package dev.animedia.languageservice.presentation.grpc.interceptor;

import io.grpc.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LanguageInterceptor implements ServerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(LanguageInterceptor.class);
	public static final Context.Key<String> LANGUAGE_CONTEXT = Context.key("language");
	private static final String DEFAULT_LANGUAGE_CODE = "en";

	@Override
	public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
		ServerCall<ReqT, RespT> call,
		Metadata headers,
		ServerCallHandler<ReqT, RespT> next
	) {
		Metadata.Key<String> languageKey = Metadata.Key.of("language", Metadata.ASCII_STRING_MARSHALLER);

		String languageCode = headers.get(languageKey);
		LOGGER.debug("Header language: {}", languageCode);
		if (languageCode == null || languageCode.isBlank()) {
			languageCode = DEFAULT_LANGUAGE_CODE;
			LOGGER.info("Using default language");
		}

		Context context = Context.current().withValue(LANGUAGE_CONTEXT, languageCode);

		return Contexts.interceptCall(context, call, headers, next);
	}

	public static String getLanguageCode() {
		String language = LANGUAGE_CONTEXT.get();
		return language != null ? language : DEFAULT_LANGUAGE_CODE;
	}

	public static String getDefaultLanguageCode() {
		return DEFAULT_LANGUAGE_CODE;
	}
}
