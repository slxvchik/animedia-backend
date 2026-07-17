package dev.animedia.contentservice.series.application.usecase.admin;

import java.util.UUID;

public interface DeleteSeriesUseCase {
	void delete(UUID id);
}
