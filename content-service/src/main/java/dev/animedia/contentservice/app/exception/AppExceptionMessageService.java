package dev.animedia.contentservice.app.exception;

import dev.animedia.contentservice.app.context.LocaleLanguageContext;
import dev.animedia.contentservice.app.exception.common.AppErrorTranslationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class AppExceptionMessageService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${application.exception.locale.path}")
    private String exceptionLocalePath;

    public List<String> getExceptionMessage(String exceptionCode, String languageCode) throws IOException {
        return this.getExceptionMessage(List.of(exceptionCode), languageCode);
    }

    public List<String> getExceptionMessage(List<String> exceptionCodes, String languageCode) throws IOException {

        String filePath = getFilePath(languageCode);

        Map<String, String> errorMessages = readErrorFile(filePath);

        String defaultLanguageCode = LocaleLanguageContext.getDefaultLocaleLanguageCode();

        if (errorMessages.isEmpty() && !languageCode.equals(defaultLanguageCode)) {
            errorMessages = readErrorFile(getFilePath(defaultLanguageCode));
        } else if (errorMessages.isEmpty()) throw new AppErrorTranslationException("File with translations not found");

        // For translations that are not found in the selected language
        List<String> notFoundTranslationCodes = new ArrayList<>();

        List<String> translations = new ArrayList<>();

        for (var exceptionCode : exceptionCodes) {
            if (!errorMessages.containsKey(exceptionCode)) notFoundTranslationCodes.add(exceptionCode);
            translations.add(errorMessages.get(exceptionCode));
        }

        if (!notFoundTranslationCodes.isEmpty()
                && !languageCode.equals(defaultLanguageCode)) {

            Map<String, String> defaultMessages = readErrorFile(getFilePath(defaultLanguageCode));

            for (var notFoundTranslationCode : notFoundTranslationCodes) {
                if (!defaultMessages.containsKey(notFoundTranslationCode)) throw new AppErrorTranslationException("Translate code not found: " + notFoundTranslationCode);
                translations.add(defaultMessages.get(notFoundTranslationCode));
            }
        }

        return translations;
    }

    private String getFilePath(String languageCode) {
        return exceptionLocalePath + languageCode + ".json";
    }

    private Map<String, String> readErrorFile(String filePath) throws IOException {

        Resource resource = new ClassPathResource(filePath);

        if (!resource.exists()) {
            return Map.of();
        }

        File file = resource.getFile();

        Map<String, String> allErrorMessages;
        try {
            allErrorMessages = objectMapper.readValue(
                file,
                new TypeReference<>() {}
            );
        } catch (Exception ex) {
            return Map.of();
        }

        return allErrorMessages;
    }
}
