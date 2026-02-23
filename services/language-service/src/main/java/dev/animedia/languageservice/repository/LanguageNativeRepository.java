package dev.animedia.languageservice.repository;

import dev.animedia.languageservice.dto.LanguageResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import java.util.*;

@Repository
public class LanguageNativeRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public LanguageNativeRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<LanguageResponseDto> languageResponseDtoRowMapper = (resultSet, rowNum) -> new LanguageResponseDto(
		resultSet.getString("code"),
		resultSet.getString("name"),
		resultSet.getString("native_name"),
		resultSet.getBoolean("is_active"),
		resultSet.getBoolean("is_default"),
		resultSet.getInt("sort_order"),
		resultSet.getString("flag_emoji")
	);

	public Page<LanguageResponseDto> searchPage(List<String> languageCodes, List<String> nativeNames, Boolean isActive, Pageable pageable) {

		List<String> whereConditions = new ArrayList<>();
		List<Object> params = new ArrayList<>();

		List<String> languageCodesWhereConditions = new ArrayList<>();
		Optional.ofNullable(languageCodes).stream().flatMap(Collection::stream)
			.filter(code -> code != null && !code.isBlank())
			.forEach(code -> {
				languageCodesWhereConditions.add("code = ?");
				params.add(code);
			});
		if (!languageCodesWhereConditions.isEmpty())
			whereConditions.add("(" + String.join(" OR ", languageCodesWhereConditions) + ")");

		List<String> nativeNamesConditions = new ArrayList<>();
		Optional.ofNullable(nativeNames).stream().flatMap(Collection::stream)
			.filter(nativeName -> nativeName != null && !nativeName.isBlank())
			.forEach(nativeName -> {
				nativeNamesConditions.add("LOWER(native_name) LIKE LOWER(?)");
				params.add("%" + nativeName + "%");
			});
		if (!nativeNamesConditions.isEmpty())
			whereConditions.add("(" + String.join(" OR ", nativeNamesConditions) + ")");

		if (isActive != null) {
			whereConditions.add("is_active = ?");
			params.add(isActive);
		}

		String whereClause = whereConditions.isEmpty() ? "" : " WHERE " + String.join(" AND ", whereConditions);

		String countSql = "SELECT COUNT(DISTINCT code) FROM language" + whereClause;

		Long total = jdbcTemplate.queryForObject(countSql, Long.class, params.toArray());

		String sql = "SELECT code, name, native_name, is_active, is_default, sort_order, flag_emoji FROM language" + whereClause + " ORDER BY code LIMIT ? OFFSET ?";

		params.add(pageable.getPageSize());
		params.add(pageable.getOffset());

		List<LanguageResponseDto> content = jdbcTemplate.query(sql, languageResponseDtoRowMapper, params.toArray());

		return new PageImpl<>(content, pageable, total != null ? total : 0);
	}
}
