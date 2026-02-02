package dev.animedia.contentservice.app.context;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocaleLanguageContext {
    private static final ThreadLocal<Locale> LANGUAGE_HOLDER = new ThreadLocal<>();
    private static final Locale DEFAULT_LANGUAGE = Locale.of("en");
    private static final Logger LOGGER = Logger.getLogger(LocaleLanguageContext.class.getName());

    private LocaleLanguageContext() {}
    
    public static void setLocaleLanguage(String languageCode) {
        if (languageCode == null || languageCode.isBlank()) {
            LANGUAGE_HOLDER.set(DEFAULT_LANGUAGE);
        } else {
            try {
                String normalizedLanguageCode = languageCode.trim().toLowerCase().substring(0, 2);
                Locale languageLocale = Locale.of(normalizedLanguageCode);
                LANGUAGE_HOLDER.set(languageLocale);
            } catch (Exception exception) {
                LOGGER.log(Level.SEVERE, "Дocale installation error: {0}", exception.getMessage());
                LANGUAGE_HOLDER.set(DEFAULT_LANGUAGE);
            }
        }
    }
        
    public static Locale getLocaleLanguage() {
        Locale language = LANGUAGE_HOLDER.get();
        if (language == null) {
            LANGUAGE_HOLDER.set(DEFAULT_LANGUAGE);
            language = DEFAULT_LANGUAGE;
        }
        return language;
    }

    public static String getLocaleLanguageCode() {
        return getLocaleLanguage().getLanguage();
    }

    public static void clear() {
        LANGUAGE_HOLDER.remove();
    }
}
