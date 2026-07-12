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
			se.getActive(),
			se.getTranslations().stream()
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

		se.setTranslations(
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