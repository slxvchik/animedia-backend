package dev.animedia.contentservice.app.context;

import java.util.Locale;

public class LanguageLocaleContext {
    private static final ThreadLocal<Locale> LANGUAGE_HOLDER = new ThreadLocal<>();
    private static final Locale DEFAULT_LANGUAGE = Locale.of("en");
    
    public static void setLanguageLocale(String languageCode) {
        if (languageCode == null || languageCode.isBlank()) {
            LANGUAGE_HOLDER.set(DEFAULT_LANGUAGE);
        } else {
            try {
                String normilizedLanguageCode = languageCode.trim().toLowerCase().substring(0, 2);
                Locale languageLocale = Locale.of(normilizedLanguageCode);
                LANGUAGE_HOLDER.set(languageLocale);
            } catch (Exception e) {
                LANGUAGE_HOLDER.set(DEFAULT_LANGUAGE);
            }
        }
    }
        
    public static Locale getLanguageLocale() {
        Locale language = LANGUAGE_HOLDER.get();
        if (language == null) {
            LANGUAGE_HOLDER.set(DEFAULT_LANGUAGE);
            language = DEFAULT_LANGUAGE;
        }
        return language;
    }

    public static String getLanguageLocaleCode() {
        return getLanguageLocale().getLanguage();
    }

    public static void clear() {
        LANGUAGE_HOLDER.remove();
    }
}
