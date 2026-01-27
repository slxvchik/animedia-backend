package dev.animedia.contentservice.app.config.interceptor;

import java.util.Arrays;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import dev.animedia.contentservice.app.context.LanguageLocaleContext;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LanguageInterceptor implements HandlerInterceptor {

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        LanguageLocaleContext.clear();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        String languageCode = Optional.ofNullable(request.getCookies())
            .stream()
            .flatMap(Arrays::stream)
            .filter(c -> "language".equals(c.getName()))
            .findFirst()
            .map(Cookie::getValue)
            .orElse(null);
            
        LanguageLocaleContext.setLanguageLocale(languageCode);
        return true;
    }
    
}