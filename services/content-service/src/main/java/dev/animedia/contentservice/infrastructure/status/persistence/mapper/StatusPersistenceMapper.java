package dev.animedia.contentservice.infrastructure.status.persistence.mapper;

import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusTranslation;
import dev.animedia.contentservice.infrastructure.status.persistence.dto.StatusTranslationRow;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StatusPersistenceMapper {
	Status toStatus(List<StatusTranslationRow> statusTranslationRow) {
		StatusTranslationRow first = statusTranslationRow.getFirst();

		Set<StatusTranslation> translationSet = statusTranslationRow.stream()
			.map(row ->
				new StatusTranslation(
					row.translationId(),
					row.languageCode(),
					row.name()
				)
			)
			.collect(Collectors.toSet());

		return new Status(
			first.id(),
			first.alias(),
			first.sortOrder(),
			translationSet
		);
	}
}
