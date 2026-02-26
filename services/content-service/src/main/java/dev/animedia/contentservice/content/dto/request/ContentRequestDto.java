package dev.animedia.contentservice.content.dto.request;

import dev.animedia.contentservice.content.ContentConstants;
import dev.animedia.contentservice.content.model.ContentType;
import dev.animedia.contentservice.genre.model.Genre;
import dev.animedia.contentservice.status.model.ContentStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public record ContentRequestDto(
	@NotNull(groups = Update.class, message = ContentConstants.UUID_REQUIRED_MESSAGE)
	UUID uuid,
	@Pattern(regexp = ContentConstants.ALIAS_REGEXP, message = ContentConstants.INVALID_ALIAS_MESSAGE)
	String alias,
	@NotNull(message = ContentConstants.TYPE_REQUIRED_MESSAGE)
	ContentType type,
	@Min(value = 1, message = ContentConstants.INVALID_SEASON_MESSAGE)
	Integer season,
	@NotNull(message = ContentConstants.STATUS_REQUIRED_MESSAGE)
	ContentStatus status,
	String coverUrl,
	String trailerUrl,
	LocalDate releaseDate,
	Boolean active,
	Integer sort,
	Set<String> languageCodes,
	Set<Genre> genres
) {
	public ContentRequestDto {
		active = Objects.requireNonNullElse(active, false);
		sort = Objects.requireNonNullElse(sort, 0);
		languageCodes = Objects.requireNonNullElse(languageCodes, new HashSet<>());
		genres = Objects.requireNonNullElse(genres, new HashSet<>());
	}
	public interface Update {}
}
