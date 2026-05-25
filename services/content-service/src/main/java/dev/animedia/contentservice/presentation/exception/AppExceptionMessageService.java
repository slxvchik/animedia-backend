package dev.animedia.contentservice.presentation.exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class AppExceptionMessageService {

    ObjectMapper objectMapper = new ObjectMapper();

    @Value("${application.exception.locale.path}")
    private String exceptionLocalePath;

    private static final Logger LOGGER = Logger.getLogger(AppExceptionMessageService.class.getName());

    private static final int MAX_CACHE_SIZE = 30;
    private final Map<String, CacheEntry> cache = Collections.synchronizedMap(
        new LinkedHashMap<>(MAX_CACHE_SIZE, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<String, CacheEntry> eldest) {
                return size() > MAX_CACHE_SIZE;
            }
        }
    );
    private record CacheEntry(Map<String, String> data, long lastModified) {}

    public String getExceptionMessage(
        String exceptionCode,
        String languageCode,
        Object... args
    ) {
        Map<String, String> errorMessages = readErrorFile(getFilePath(languageCode));
        String errorMessage = errorMessages.getOrDefault(
            exceptionCode,
            errorMessages.getOrDefault(
                "default",
                "An error has occurred, please refresh the page or try again later"
            )
        );

        if (args != null && args.length > 0) {
            try {
                return MessageFormat.format(errorMessage, args);
            } catch (IllegalArgumentException e) {
                return errorMessage;
            }
        }

        return errorMessage;
    }

    private String getFilePath(String languageCode) {
        return exceptionLocalePath + "/" + languageCode + ".json";
    }

    private Map<String, String> readErrorFile(String filePath) {
        try {
            Path path = Path.of(filePath);
            if (!Files.exists(path)) return Map.of();

            long currentModified = Files.getLastModifiedTime(path).toMillis();
            CacheEntry cached = cache.get(filePath);

            if (cached != null && cached.lastModified() == currentModified) return cached.data();

            synchronized (this) {
                Map<String, String> messages = objectMapper.readValue(
                    path.toFile(),
                    new TypeReference<>() {}
                );
                cache.put(filePath, new CacheEntry(messages, currentModified));
                return messages;
            }

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Error reading a file with translation errors: {0}", ex.getMessage());
            return Map.of();
        }
    }
}
