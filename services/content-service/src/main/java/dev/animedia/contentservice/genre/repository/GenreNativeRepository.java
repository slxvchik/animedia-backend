package dev.animedia.contentservice.genre.repository;

import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationResponseDto;
import dev.animedia.contentservice.genre.dto.response.GenreWithTranslationListResponseDto;
import dev.animedia.contentservice.genre.mapper.GenreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

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

	public Page<GenreWithTranslationListResponseDto> searchPage(List<String> aliases, List<String> names, List<String> languageCodes, Pageable pageable) {

		List<String> whereConditions = new ArrayList<>();
		List<Object> params = new ArrayList<>();

		List<String> aliasesWhereConditions = new ArrayList<>();
		Optional.ofNullable(aliases).stream().flatMap(Collection::stream)
			.filter(alias -> alias != null && !alias.isBlank())
			.forEach(alias -> {
				aliasesWhereConditions.add("LOWER(g.alias) LIKE LOWER(?)");
				params.add("%" + alias + "%");
			});
		if (!aliasesWhereConditions.isEmpty())
			whereConditions.add("(" + String.join(" OR ", aliasesWhereConditions) + ")");

		List<String> namesWhereConditions = new ArrayList<>();
		Optional.ofNullable(names).stream().flatMap(Collection::stream)
			.filter(name -> name != null && !name.isBlank())
			.forEach(name -> {
				namesWhereConditions.add("LOWER(t.name) LIKE LOWER(?)");
				params.add("%" + name + "%");
			});
		if (!namesWhereConditions.isEmpty())
			whereConditions.add("(" + String.join(" OR ", namesWhereConditions) + ")");

		List<String> languageCodesWhereConditions = new ArrayList<>();
		Optional.ofNullable(languageCodes).stream().flatMap(Collection::stream)
			.filter(code -> code != null && !code.isBlank())
			.forEach(code -> {
				languageCodesWhereConditions.add("code = ?");
				params.add(code);
			});
		if (!languageCodesWhereConditions.isEmpty())
			whereConditions.add("(" + String.join(" OR ", languageCodesWhereConditions) + ")");

		String whereClause = whereConditions.isEmpty() ? "" : " WHERE " + String.join(" AND ", whereConditions);

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

		List<GenreWithTranslationListResponseDto> genresWithTranslations = genreMapper.toGenreListWithTranslationListResponseDto(content);

		return new PageImpl<>(genresWithTranslations, pageable, total != null ? total : 0);
	}

}
