package dev.animedia.contentservice.content.repository;

import dev.animedia.contentservice.content.dto.request.CommonSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.*;

@Repository
public class ContentSearchRepositoryImpl implements ContentSearchRepository {

	private final JdbcTemplate jdbcTemplate;

	private final RowMapper<UUID> rowMapper = (resultSet, _) -> resultSet.getObject("uuid", UUID.class);

	@Autowired
	public ContentSearchRepositoryImpl(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Page<UUID> search(PrivateSearchRequestDto searchRequestDto) {
		return executeSearch(searchRequestDto, false, searchRequestDto.pageable());
	}

	@Override
	public Page<UUID> search(PublicSearchRequestDto searchRequestDto) {
		return executeSearch(searchRequestDto, true, searchRequestDto.pageable());
	}

	private void buildConditions(List<String> conditions, List<Object> params, CommonSearchRequestDto r, boolean isPrivate) {
		if (StringUtils.hasText(r.alias())) {
			conditions.add("LOWER(c.alias) LIKE LOWER(?)");
			params.add("%" + r.alias() + "%");
		}
		if (r.type() != null) {
			conditions.add("c.type = ?");
			params.add(r.type().name());
		}
		if (r.seasons() != null && !r.seasons().isEmpty()) {
			var seasonsList = r.seasons().stream().filter(Objects::nonNull).toList();
			conditions.add("c.season IN (" + buildInPlaceholders(seasonsList.size()) + ")");
			params.addAll(seasonsList);
		}
		if (r.contentStatusIds() != null && !r.contentStatusIds().isEmpty()) {
			var contentStatusIdsList = r.contentStatusIds().stream().filter(Objects::nonNull).toList();
			conditions.add("c.status_id IN (" + buildInPlaceholders(contentStatusIdsList.size()) + ")");
			params.addAll(contentStatusIdsList);
		}
		if (r.releaseFrom() != null) {
			conditions.add("c.release_date >= ?");
			params.add(r.releaseFrom());
		}
		if (r.releaseTo() != null) {
			conditions.add("c.release_date <= ?");
			params.add(r.releaseTo());
		}
		if (r.languageCodes() != null && !r.languageCodes().isEmpty()) {
			var languageCodesList = r.languageCodes().stream().filter(Objects::nonNull).toList();
			conditions.add("cl.language_code IN (" + buildInPlaceholders(languageCodesList.size()) + ")");
			params.addAll(languageCodesList);
		}
		if (r.genreIds() != null && !r.genreIds().isEmpty()) {
			var genreIdsList = r.genreIds().stream().filter(Objects::nonNull).toList();
			conditions.add("cg.genre_id IN (" + buildInPlaceholders(genreIdsList.size()) + ")");
			params.addAll(genreIdsList);
		}

		if (isPrivate && r instanceof PrivateSearchRequestDto pr) {
			if (pr.uuid() != null) {
				conditions.add("c.uuid = ?");
				params.add(pr.uuid());
			}
			if (pr.createdAtFrom() != null) {
				conditions.add("c.created_at >= ?");
				params.add(pr.createdAtFrom());
			}
			if (pr.createdAtTo() != null) {
				conditions.add("c.created_at <= ?");
				params.add(pr.createdAtTo());
			}
			if (pr.updatedAtFrom() != null) {
				conditions.add("c.updated_at >= ?");
				params.add(pr.updatedAtFrom());
			}
			if (pr.updatedAtTo() != null) {
				conditions.add("c.updated_at <= ?");
				params.add(pr.updatedAtTo());
			}
			if (pr.active() != null) {
				conditions.add("c.active = ?");
				params.add(pr.active());
			}
		}
	}

	private Page<UUID> executeSearch(
		CommonSearchRequestDto request,
		boolean isPrivate,
		Pageable pageable
	) {
		List<String> conditions = new ArrayList<>();
		List<Object> params = new ArrayList<>();

		buildConditions(conditions, params, request, isPrivate);

		String selectCnt = "SELECT COUNT(DISTINCT c.uuid) FROM content c";
		String select = "SELECT DISTINCT c.uuid FROM content c";

		String joinTables = buildJoinedTables(request);
		String where = buildWhereClause(conditions);

		String sqlCnt = selectCnt + joinTables + where;
		String sql = select + joinTables + where;

		Long total = jdbcTemplate.queryForObject(sqlCnt, Long.class, params.toArray());
		if (total == null || total == 0) return Page.empty(pageable);

		String paginatedQuery = sql + " LIMIT ? OFFSET ?";

		List<Object> paginatedParams = new ArrayList<>(params);
		paginatedParams.add(pageable.getPageSize());
		paginatedParams.add(pageable.getOffset());

		List<UUID> contentIds = jdbcTemplate.query(paginatedQuery, rowMapper, paginatedParams.toArray());

		return new PageImpl<>(contentIds, pageable, total);
	}

	private String buildWhereClause(
		List<String> conditions
	) {
		StringBuilder sql = new StringBuilder();
		if (!conditions.isEmpty()) sql.append(" WHERE ").append(String.join(" AND ", conditions));
		sql.append(" ORDER BY c.sort ASC, c.release_date DESC, c.alias ASC");
		return sql.toString();
	}

	private String buildJoinedTables(CommonSearchRequestDto request) {
		StringBuilder sql = new StringBuilder();
		if (hasGenreFilter(request)) {
			sql.append(" LEFT JOIN content_genres cg ON c.uuid = cg.content_uuid");
		}
		if (hasLanguageFilter(request)) {
			sql.append(" LEFT JOIN content_languages cl ON c.uuid = cl.content_uuid");
		}
		if (hasTranslationFilter(request)) {
			sql.append(" LEFT JOIN content_translation ct ON c.uuid = ct.content_uuid");
		}
		return sql.toString();
	}

	private boolean hasGenreFilter(CommonSearchRequestDto r) {
		return r.genreIds() != null && !r.genreIds().isEmpty();
	}

	private boolean hasLanguageFilter(CommonSearchRequestDto r) {
		return r.languageCodes() != null && !r.languageCodes().isEmpty();
	}

	private boolean hasTranslationFilter(CommonSearchRequestDto r) {
		return r.title() != null && !r.title().isBlank();
	}

	private String buildInPlaceholders(int count) {
		return String.join(", ", Collections.nCopies(count, "?"));
	}
}
