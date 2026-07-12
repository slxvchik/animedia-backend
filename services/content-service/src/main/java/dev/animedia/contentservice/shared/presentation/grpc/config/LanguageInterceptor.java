package dev.animedia.contentservice.shared.presentation.grpc.config;

import io.grpc.*;

public class LanguageInterceptor implements ServerInterceptor {
    public static final Context.Key<String> LANGUAGE_CONTEXT = Context.key("language");
    private static final String DEFAULT_LANGUAGE_CODE = "en";
    private static final Metadata.Key<String> languageKey = Metadata.Key.of("language", Metadata.ASCII_STRING_MARSHALLER);

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(
        ServerCall<ReqT, RespT> call,
        Metadata headers,
        ServerCallHandler<ReqT, RespT> next
    ) {

        String languageCode = headers.get(languageKey);
        if (languageCode == null || languageCode.isBlank()) {
            languageCode = DEFAULT_LANGUAGE_CODE;
        }

        Context context = Context.current().withValue(LANGUAGE_CONTEXT, languageCode);

        return Contexts.interceptCall(context, call, headers, next);
    }

    public static String getLanguageCode() {
        String language = LANGUAGE_CONTEXT.get();
        return language != null ? language : DEFAULT_LANGUAGE_CODE;
    }
}