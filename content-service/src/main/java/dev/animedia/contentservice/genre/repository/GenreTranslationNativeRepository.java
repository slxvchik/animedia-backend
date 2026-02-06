package dev.animedia.contentservice.genre.repository;

import dev.animedia.contentservice.genre.dto.response.GenreTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreTranslationMapper;
import dev.animedia.contentservice.genre.model.GenreTranslation;
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
public class GenreTranslationNativeRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public GenreTranslationNativeRepository(
		JdbcTemplate jdbcTemplate
	) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<GenreTranslationResponseDto> genreTranslationResponseDtoRowMapper = (resultSet, rowNum) -> new GenreTranslationResponseDto(
		resultSet.getLong("id"),
		resultSet.getLong("genre_id"),
		resultSet.getString("language_code"),
		resultSet.getString("name"),
		resultSet.getString("description")
	);

	public Page<GenreTranslationResponseDto> searchPage(String name, Long genreId, List<String> languageCodes, Pageable pageable) {

		List<String> whereConditions = new ArrayList<>();
		List<Object> params = new ArrayList<>();

		if (name != null && !name.isBlank()) {
			whereConditions.add("LOWER(t.name) LIKE LOWER(?)");
			params.add(name);
		}

		if (genreId != null) {
			whereConditions.add("t.genre_id = ?");
			params.add(genreId);
		}

		if (languageCodes != null && !languageCodes.isEmpty()) {
			List<String> languagesWhereConditions = new ArrayList<>();
			languageCodes.forEach(langCode -> {
				if (!langCode.isBlank()) {
					languagesWhereConditions.add("t.language_code = ?");
					params.add(langCode);
				}
			});
			whereConditions.add("(" + String.join(" OR ", languagesWhereConditions) + ")");
		}

		String whereClause = whereConditions.isEmpty() ? "" : " WHERE " + String.join(" AND ", whereConditions);

		String countSql = "SELECT COUNT(DISTINCT t.id) FROM genre_translation t LEFT JOIN genre g ON t.genre_id = g.id" + whereClause;

		Long total = jdbcTemplate.queryForObject(countSql, Long.class, params.toArray());

		String sql = "SELECT t.id, t.name, t.description, t.language_code FROM genre_translation t" + whereClause + " ORDER BY t.id LIMIT ? OFFSET ?";

		params.add(pageable.getPageSize());
		params.add(pageable.getOffset());

		List<GenreTranslationResponseDto> content = jdbcTemplate.query(
			sql,
			genreTranslationResponseDtoRowMapper,
			params.toArray()
		);

		return new PageImpl<>(content, pageable, total != null ? total : 0);
	}
}
