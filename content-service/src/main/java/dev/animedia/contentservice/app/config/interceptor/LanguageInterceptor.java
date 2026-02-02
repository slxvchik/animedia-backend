package dev.animedia.contentservice.app.config.interceptor;

import java.util.Arrays;
import java.util.Optional;

import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import dev.animedia.contentservice.app.context.LocaleLanguageContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LanguageInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler,
                                @Nullable Exception ex) throws Exception {
        LocaleLanguageContext.clear();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler)
            throws Exception {
        
        String languageCode = Optional.ofNullable(request.getCookies())
            .stream()
            .flatMap(Arrays::stream)
            .filter(c -> "language".equals(c.getName()))
            .findFirst()
            .map(Cookie::getValue)
            .orElse(null);
            
        LocaleLanguageContext.setLocaleLanguage(languageCode);
        return true;
    }
    
}