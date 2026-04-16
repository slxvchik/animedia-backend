package dev.animedia.contentservice.application.content.usecase;

import java.util.UUID;

public interface DeleteContentTranslationUseCase {
	void deleteTranslation(UUID contentUuid, UUID contentTranslationUUID);
}
