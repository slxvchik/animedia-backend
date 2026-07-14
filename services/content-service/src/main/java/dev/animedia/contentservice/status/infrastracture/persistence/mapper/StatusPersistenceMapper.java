package dev.animedia.contentservice.status.infrastracture.persistence.mapper;

import dev.animedia.contentservice.status.domain.model.Status;
import dev.animedia.contentservice.status.domain.model.StatusTranslation;
import dev.animedia.contentservice.status.infrastracture.persistence.model.StatusEntity;
import dev.animedia.contentservice.status.infrastracture.persistence.model.StatusTranslationEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StatusPersistenceMapper {

	/**
	 * To domain statusId from jpa statusId entity
	 * @param se jpa statusId
	 * @return domain statusId
	 */
	public Status toStatus(StatusEntity se) {
		if (se == null) return null;
		return new Status(
			se.getId(),
			se.getAlias(),
			se.getSortOrder(),
			se.getActive(),
			se.getTranslations().stream()
				.map(this::toStatusTranslation)
				.collect(Collectors.toSet())
		);
	}

	/**
	 * To domain statusId translation from jpa statusId translation entity
	 * @param ste jpa statusId translation entity
	 * @return domain statusId translation
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
	 * To jpa statusId entity from domain statusId
	 * @param status domain statusId
	 * @return jpa statusId entity
	 */
	public StatusEntity toStatusEntity(Status status) {
		if (status == null) return null;

		StatusEntity se = new StatusEntity();

		se.setId(status.getId());
		se.setAlias(status.getAlias());
		se.setSortOrder(status.getSortOrder());

		se.setTranslations(
			status.getTranslations().stream()
				.map(ste -> toStatusTranslationEntity(ste, se))
				.collect(Collectors.toSet())
		);

		return se;
	}

	/**
	 * To jpa statusId translation entity from domain statusId translation
	 * @param statusTranslation domain statusId translation
	 * @param statusEntity jpa statusId entity
	 * @return jpa statusId translation entity
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