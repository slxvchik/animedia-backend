package dev.animedia.contentservice.genre.application.dto.request;

import java.util.Set;
import java.util.UUID;

public record UpdateGenreDto(
	UUID id,
	Integer sortOrder,
	Boolean active,
	Set<UpdateGenreTranslationDto> translations
) {}