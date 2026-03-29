package dev.animedia.languageservice.infrastructure.persistence.repository;

import dev.animedia.languageservice.application.dto.Page;
import dev.animedia.languageservice.application.dto.Pageable;
import dev.animedia.languageservice.domain.model.Language;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class LanguageQueryRepository implements dev.animedia.languageservice.domain.repository.LanguageQueryRepository {

	private final JdbcTemplate jdbcTemplate;

	@Autowired
	public LanguageQueryRepository(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private final RowMapper<Language> languageResponseDtoRowMapper = (resultSet, rowNum) -> new Language(
		resultSet.getString("code"),
		resultSet.getString("name"),
		resultSet.getBoolean("is_active"),
		resultSet.getBoolean("is_default"),
		resultSet.getInt("sort_order"),
		resultSet.getString("flag_emoji")
	);

	@Override
	public Page<Language> search(List<String> languageCodes, List<String> names, Boolean isActive, Pageable pageable) {

		List<String> whereConditions = new ArrayList<>();
		List<Object> params = new ArrayList<>();

		if (languageCodes != null && !languageCodes.isEmpty()) {
			whereConditions.add("code IN (" + String.join(",", Collections.nCopies(languageCodes.size(), "?")) + ")");
			params.addAll(languageCodes);
		}

		List<String> namesConditions = new ArrayList<>();
		Optional.ofNullable(names).stream().flatMap(Collection::stream)
			.filter(name -> name != null && !name.isBlank())
			.forEach(name -> {
				namesConditions.add("LOWER(name) LIKE LOWER(?)");
				params.add("%" + name + "%");
			});
		if (!namesConditions.isEmpty())
			whereConditions.add("(" + String.join(" OR ", namesConditions) + ")");

		if (isActive != null) {
			whereConditions.add("is_active = ?");
			params.add(isActive);
		}

		String whereClause = whereConditions.isEmpty() ? "" : " WHERE " + String.join(" AND ", whereConditions);

		String countSql = "SELECT COUNT(code) FROM language" + whereClause;

		Long total = jdbcTemplate.queryForObject(countSql, Long.class, params.toArray());

		String sql = "SELECT code, name, is_active, is_default, sort_order, flag_emoji FROM language" + whereClause + " ORDER BY code LIMIT ? OFFSET ?";

		params.add(pageable.size());
		Long offset = pageable.size() * pageable.page();
		params.add(offset);

		List<Language> content = jdbcTemplate.query(sql, languageResponseDtoRowMapper, params.toArray());

		long totalPages = (total + pageable.size() - 1) / pageable.size();
		long currentPage = pageable.page();

		return new Page<>(
			content,
			total,
			totalPages,
			currentPage,
			pageable.size(),
			currentPage == 0,
			currentPage >= totalPages - 1,
			currentPage < totalPages - 1,
			currentPage > 0
		);
	}

	@Override
	public Optional<Language> findByCode(String code) {
		String sql = "SELECT code, name, is_active, is_default, sort_order, flag_emoji FROM language WHERE code = ?";
		return jdbcTemplate.query(sql, languageResponseDtoRowMapper, List.of(code))
			.stream()
			.findFirst();
	}
}
