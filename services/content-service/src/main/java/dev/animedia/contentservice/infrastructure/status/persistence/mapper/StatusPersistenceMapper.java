package dev.animedia.contentservice.infrastructure.status.persistence.mapper;

import dev.animedia.contentservice.domain.status.model.Status;
import dev.animedia.contentservice.domain.status.model.StatusTranslation;
import dev.animedia.contentservice.infrastructure.status.persistence.dto.StatusTranslationRowDto;
import dev.animedia.contentservice.infrastructure.status.persistence.model.StatusEntity;
import dev.animedia.contentservice.infrastructure.status.persistence.model.StatusTranslationEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StatusPersistenceMapper {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * To one status with translations from db row list
	 * @param statusTranslationRowDto status with translation row list from db
	 * @return one domain status with translations
	 */
	public Status toStatus(List<StatusTranslationRowDto> statusTranslationRowDto) {
		if (statusTranslationRowDto.isEmpty()) return null;

		StatusTranslationRowDto first = statusTranslationRowDto.getFirst();

		Set<StatusTranslation> translationSet = statusTranslationRowDto.stream()
			.map(row -> {
				if (row.translationId() == null) return null;
				return new StatusTranslation(
					row.translationId(),
					row.languageCode(),
					row.name()
				);
			})
			.filter(Objects::nonNull)
			.collect(Collectors.toSet());

		return new Status(
			first.id(),
			first.alias(),
			first.sortOrder(),
			translationSet
		);
	}

	/**
	 * To multiple status list with translation list from db row list
	 * @param statusTranslationRowDtoList status with translation row list from db
	 * @return domain status list with translations
	 */
	public List<Status> toStatusList(List<StatusTranslationRowDto> statusTranslationRowDtoList) {
		if (statusTranslationRowDtoList.isEmpty()) return List.of();

		// Map by Status id
		Map<Long, List<StatusTranslationRowDto>> statusMap = statusTranslationRowDtoList.stream()
			.collect(Collectors.groupingBy(StatusTranslationRowDto::id));

		return statusMap.values().stream()
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
			.toList();
	}

	/**
	 * To domain status from jpa status entity
	 * @param se jpa status
	 * @return domain status
	 */
	public Status toStatus(StatusEntity se) {
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
		StatusEntity se = status.getId() == null ? new StatusEntity() : entityManager.getReference(StatusEntity.class, status.getId());

		se.setAlias(status.getAlias());
		se.setSortOrder(status.getSortOrder());

		Set<StatusTranslationEntity> steSet = status.getTranslationSet().stream()
			.map(ste -> this.toStatusTranslationEntity(ste, se))
			.collect(Collectors.toSet());

		se.setTranslationSet(steSet);

		return se;
	}

	/**
	 * To jpa status translation entity from domain status translation
	 * @param statusTranslation domain status translation
	 * @param statusEntity jpa status entity
	 * @return jpa status translation entity
	 */
	public StatusTranslationEntity toStatusTranslationEntity(StatusTranslation statusTranslation, StatusEntity statusEntity) {
		StatusTranslationEntity ste = statusTranslation.getId() == null ? new StatusTranslationEntity() : entityManager.getReference(StatusTranslationEntity.class, statusTranslation.getId());

		ste.setStatusEntity(statusEntity);

		ste.setName(statusTranslation.getName());
		ste.setLanguageCode(statusTranslation.getLanguageCode());

		return ste;
	}
}