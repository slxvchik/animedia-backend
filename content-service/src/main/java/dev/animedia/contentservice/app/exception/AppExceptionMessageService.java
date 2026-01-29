package dev.animedia.contentservice.app.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
public class AppExceptionMessageService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${application.exception.locale.path}")
    private String exceptionLocalePath;

    public String getExceptionMessage(String exceptionCode, String languageCode) throws IOException {

        String filePath = exceptionLocalePath + languageCode + ".json";

        Resource resource = new ClassPathResource(filePath);

        if (!resource.exists()) {
            throw new ErrorLocaleException("Locale file not found: " + filePath + "; Exception code: " + exceptionCode);
        }

        Map<String, String> errorMessages;
        try (InputStream inputStream = resource.getInputStream()) {
            errorMessages = objectMapper.readValue(
                    inputStream,
                    new TypeReference<Map<String, String>>() {}
            );
        }

        String errorMessage = errorMessages.get(exceptionCode);
        if (errorMessage == null || errorMessage.isEmpty()) {
            throw new ErrorLocaleException("Translate for error not found; Exception code: " + exceptionCode);
        }

        return errorMessage;
    }
}
