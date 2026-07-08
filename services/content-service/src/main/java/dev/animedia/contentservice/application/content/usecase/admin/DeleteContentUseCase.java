package dev.animedia.contentservice.application.content.usecase.admin;

import java.util.UUID;

public interface DeleteContentUseCase {
	void delete(UUID uuid);
}
