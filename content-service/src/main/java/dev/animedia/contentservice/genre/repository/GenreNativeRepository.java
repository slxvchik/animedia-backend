package dev.animedia.contentservice.genre.repository;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationsResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class GenreNativeRepository {

	private final JdbcTemplate jdbcTemplate;
	private final GenreMapper genreMapper;

	@Autowired
	public GenreNativeRepository(JdbcTemplate jdbcTemplate, GenreMapper genreMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.genreMapper = genreMapper;
	}

	private final RowMapper<GenreWithTranslationResponseDto> genreWithTranslationResponseDtoRowMapper = (resultSet, rowNum) -> {

		Long translationId = null;
		long temp = resultSet.getLong("translation_id");
		if (!resultSet.wasNull()) {
			translationId = temp;
		}

		return new GenreWithTranslationResponseDto(
			resultSet.getLong("id"),
			resultSet.getString("alias"),
			resultSet.getLong("sort"),
			translationId,
			resultSet.getString("language_code"),
			resultSet.getString("name"),
			resultSet.getString("description")
		);
	};

	public Page<GenreWithTranslationsResponseDto> searchPage(String alias, List<String> languageCodes, Pageable pageable) {

		List<String> whereConditions = new ArrayList<>();
		List<Object> params = new ArrayList<>();

		if (alias != null && !alias.isBlank()) {
			whereConditions.add("LOWER(g.alias) LIKE LOWER(?)");
			params.add("%" + alias + "%");
		}

		if (languageCodes != null && !languageCodes.isEmpty()) {
			List<String> languagesWhereClause = new ArrayList<>();
			languageCodes.forEach(langCode -> {
				languagesWhereClause.add(" OR t.language_code = ?");
				params.add(langCode);
			});
			whereConditions.add("(" + languagesWhereClause.toString().replaceFirst(" OR ", "") + ")");
		}

		String whereClause = whereConditions.isEmpty()
			? ""
			: " WHERE " + String.join(" AND ", whereConditions);

		String countSql = "SELECT COUNT(DISTINCT g.id) FROM genre g LEFT JOIN genre_translation t ON g.id = t.genre_id" + whereClause;

		Long total = jdbcTemplate.queryForObject(countSql, Long.class, params.toArray());

		String sql = "SELECT g.id, g.alias, g.sort, t.id as translation_id, t.name, t.description, t.language_code FROM genre g LEFT JOIN genre_translation t ON g.id = t.genre_id" + whereClause + " ORDER BY g.sort LIMIT ? OFFSET ?";

		params.add(pageable.getPageSize());
		params.add(pageable.getOffset());

		List<GenreWithTranslationResponseDto> content = jdbcTemplate.query(
			sql,
			genreWithTranslationResponseDtoRowMapper,
			params.toArray()
		);

		List<GenreWithTranslationsResponseDto> genresWithTranslations = genreMapper.toGenresWithTranslationsResponseDto(content);

		return new PageImpl<>(genresWithTranslations, pageable, total != null ? total : 0);
	}

}
