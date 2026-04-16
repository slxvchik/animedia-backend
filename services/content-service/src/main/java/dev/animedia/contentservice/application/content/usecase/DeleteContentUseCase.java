package dev.animedia.contentservice.application.content.usecase;

import java.util.UUID;

public interface DeleteContentUseCase {
	void delete(UUID uuid);
}
