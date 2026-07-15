package dev.animedia.contentservice.series.domain.model;

import dev.animedia.contentservice.shared.domain.translation.model.BaseTranslation;

import java.util.UUID;

public class SeriesTranslation extends BaseTranslation {
	private final UUID id;
	private String name;
	private String description;
}
