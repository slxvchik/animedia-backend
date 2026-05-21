package dev.animedia.contentservice.infrastructure.persistence.status.mapper;

import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusTranslation;
import dev.animedia.contentservice.infrastructure.persistence.status.dto.StatusTranslationRowDto;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusEntity;
import dev.animedia.contentservice.infrastructure.persistence.status.model.StatusTranslationEntity;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class StatusPersistenceMapper {

	/**
	 * To multiple status list with translation list from db row list
	 * @param statusTranslationRowDtoList status with translation row list from db
	 * @return domain status list with translations
	 */
	public List<Status> toStatusList(List<StatusTranslationRowDto> statusTranslationRowDtoList) {
		if (statusTranslationRowDtoList == null || statusTranslationRowDtoList.isEmpty()) return List.of();

		// Map by Status id
		Map<Long, List<StatusTranslationRowDto>> statusRowMap = statusTranslationRowDtoList.stream()
			.collect(Collectors.groupingBy(StatusTranslationRowDto::id));

		return statusRowMap.values().stream()
			.map(rows -> {
				StatusTranslationRowDto firstRow = rows.getFirst();

				Set<StatusTranslation> translationSet = rows.stream()
					.map(statusTranslationRowDto ->
						new StatusTranslation(
							statusTranslationRowDto.translationId(),
							statusTranslationRowDto.languageCode(),
							statusTranslationRowDto.name()
						)
					)
					.collect(Collectors.toSet());

				return new Status(
					firstRow.id(),
					firstRow.alias(),
					firstRow.sortOrder(),
					translationSet
				);
			})
			.sorted(Comparator.comparing(Status::getSortOrder))
			.toList();
	}

	/**
	 * To domain status from jpa status entity
	 * @param se jpa status
	 * @return domain status
	 */
	public Status toStatus(StatusEntity se) {
		if (se == null) return null;
		return new Status(
			se.getId(),
			se.getAlias(),
			se.getSortOrder(),
			se.getTranslationSet().stream()
				.map(this::toStatusTranslation)
				.collect(Collectors.toSet())
		);
	}

	/**
	 * To domain status translation from jpa status translation entity
	 * @param ste jpa status translation entity
	 * @return domain status translation
	 */
	public StatusTranslation toStatusTranslation(StatusTranslationEntity ste) {
		if (ste == null) return null;
		return new StatusTranslation(
			ste.getId(),
			ste.getLanguageCode(),
			ste.getName()
		);
	}

	/**
	 * To jpa status entity from domain status
	 * @param status domain status
	 * @return jpa status entity
	 */
	public StatusEntity toStatusEntity(Status status) {
		if (status == null) return null;

		StatusEntity se = new StatusEntity();

		se.setId(status.getId());
		se.setAlias(status.getAlias());
		se.setSortOrder(status.getSortOrder());

		se.setTranslationSet(
			status.getTranslationSet().stream()
				.map(ste -> toStatusTranslationEntity(ste, se))
				.collect(Collectors.toSet())
		);

		return se;
	}

	/**
	 * To jpa status translation entity from domain status translation
	 * @param statusTranslation domain status translation
	 * @param statusEntity jpa status entity
	 * @return jpa status translation entity
	 */
	public StatusTranslationEntity toStatusTranslationEntity(StatusTranslation statusTranslation, StatusEntity statusEntity) {
		if (statusTranslation == null) return null;

		StatusTranslationEntity ste = new StatusTranslationEntity();

		ste.setId(statusTranslation.getId());
		ste.setStatusEntity(statusEntity);
		ste.setLanguageCode(statusTranslation.getLanguageCode());
		ste.setName(statusTranslation.getName());

		return ste;
    }
}