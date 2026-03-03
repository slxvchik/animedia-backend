//package dev.animedia.contentservice.content.repository;
//
//import dev.animedia.contentservice.content.dto.request.CommonSearchRequestDto;
//import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
//import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
//import dev.animedia.contentservice.content.model.Content;
//import dev.animedia.contentservice.content.model.ContentTranslation;
//import dev.animedia.contentservice.genre.model.Genre;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.criteria.*;
//import org.jspecify.annotations.Nullable;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.*;
//
//@Repository
//public class ContentSearchRepositoryImpl implements ContentSearchRepository {
//
//	private static final String COLUMN_ID = "uuid";
//	private static final String COLUMN_ALIAS = "alias";
//	private static final String COLUMN_TITLE = "title";
//	private static final String COLUMN_TYPE = "type";
//	private static final String COLUMN_SEASON = "season";
//	private static final String COLUMN_STATUS = "status";
//	private static final String COLUMN_STATUS_ID = "id";
//	private static final String COLUMN_RELEASE_DATE = "releaseDate";
//	private static final String COLUMN_LANGUAGE_CODES = "languageCodes";
//    private static final String COLUMN_LANGUAGE_CODE = "languageCode";
//	private static final String COLUMN_CREATED_AT = "createdAt";
//	private static final String COLUMN_UPDATED_AT = "updatedAt";
//	private static final String COLUMN_SORT = "sort";
//	private static final String COLUMN_ACTIVE = "active";
//
//	private static final String TABLE_TRANSLATIONS = "translations";
//	private static final String TABLE_GENRES = "genres";
//	private static final String COLUMN_GENRE_ID = "id";
//
//
//	@PersistenceContext
//    private EntityManager entityManager;
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<UUID> search(PrivateSearchRequestDto searchRequestDto) {
//        return executeSearch(searchRequestDto, null, searchRequestDto.pageable());
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public Page<UUID> search(PublicSearchRequestDto searchRequestDto, String languageCode) {
//        return executeSearch(searchRequestDto, languageCode, searchRequestDto.pageable());
//    }
//
//    private Page<UUID> executeSearch(CommonSearchRequestDto request, @Nullable String languageCode, Pageable pageable) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//
//        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
//        Root<Content> countRoot = countQuery.from(Content.class);
//
//        List<Predicate> countPredicates = buildPredicates(request, languageCode, cb, countRoot);
//
//        countQuery.select(cb.countDistinct(countRoot.get(COLUMN_ID)));
//        if (!countPredicates.isEmpty()) {
//            countQuery.where(countPredicates.toArray(new Predicate[0]));
//        }
//
//        Long total = entityManager.createQuery(countQuery).getSingleResult();
//
//        if (total == 0) {
//            return Page.empty(pageable);
//        }
//
//        CriteriaQuery<UUID> query = cb.createQuery(UUID.class);
//        Root<Content> contentRoot = query.from(Content.class);
//
//        List<Predicate> dataPredicates = buildPredicates(request, languageCode, cb, contentRoot);
//
//        query.select(contentRoot.get(COLUMN_ID)).distinct(true);
//        if (!dataPredicates.isEmpty()) {
//            query.where(dataPredicates.toArray(new Predicate[0]));
//        }
//
//        if (pageable.getSort().isSorted()) {
//            query.orderBy(getOrders(pageable.getSort(), cb, contentRoot));
//        }
//
//        int offset = (int) Math.min(pageable.getOffset(), Integer.MAX_VALUE);
//        int limit = pageable.getPageSize();
//
//        List<UUID> contentIds = entityManager.createQuery(query)
//            .setFirstResult(offset)
//            .setMaxResults(limit)
//            .getResultList();
//
//        return new PageImpl<>(contentIds, pageable, total);
//    }
//
//    private List<Predicate> buildPredicates(CommonSearchRequestDto r, String languageCode, CriteriaBuilder cb, Root<Content> contentRoot) {
//        List<Predicate> predicates = new ArrayList<>();
//
//        buildCommonPredicates(predicates, r, languageCode, cb, contentRoot);
//
//        if (r instanceof PrivateSearchRequestDto pr) {
//            buildPrivatePredicates(predicates, pr, cb, contentRoot);
//        }
//
//        return predicates;
//    }
//
//    private void buildCommonPredicates(
//        List<Predicate> predicates,
//        CommonSearchRequestDto r,
//        String languageCode,
//        CriteriaBuilder cb,
//        Root<Content> contentRoot
//    ) {
//        if (r.alias() != null && !r.alias().isBlank()) {
//            predicates.add(
//                cb.like(contentRoot.get(COLUMN_ALIAS),
//                "%" + r.alias() + "%")
//            );
//        }
//        if (r.title() != null && !r.title().isBlank() || languageCode != null && !languageCode.isBlank()) {
//            Join<Content, ContentTranslation> translationJoin = contentRoot.join(TABLE_TRANSLATIONS);
//            if (r.title() != null && !r.title().isBlank()) {
//                predicates.add(cb.like(translationJoin.get(COLUMN_TITLE), "%" + r.title() + "%"));
//            }
//            if (languageCode != null && !languageCode.isBlank()) {
//                predicates.add(cb.equal(translationJoin.get(COLUMN_LANGUAGE_CODE), languageCode));
//            }
//        }
//        if (r.type() != null) {
//            predicates.add(cb.equal(contentRoot.get(COLUMN_TYPE), r.type()));
//        }
//        if (r.seasons() != null && !r.seasons().isEmpty()) {
//            var seasons = r.seasons().stream()
//                .filter(Objects::nonNull)
//                .toList();
//            if (!seasons.isEmpty()) {
//                predicates.add(contentRoot.get(COLUMN_SEASON).in(seasons));
//            }
//        }
//        if (r.contentStatusIds() != null && !r.contentStatusIds().isEmpty()) {
//            var statusIds = r.contentStatusIds().stream()
//                .filter(Objects::nonNull)
//                .toList();
//            if (!statusIds.isEmpty()) {
//                predicates.add(contentRoot.get(COLUMN_STATUS).get(COLUMN_STATUS_ID).in(statusIds));
//            }
//        }
//        if (r.releaseFrom() != null) {
//            predicates.add(cb.greaterThanOrEqualTo(contentRoot.get(COLUMN_RELEASE_DATE), r.releaseFrom()));
//        }
//        if (r.releaseTo() != null) {
//            predicates.add(cb.lessThanOrEqualTo(contentRoot.get(COLUMN_RELEASE_DATE), r.releaseTo()));
//        }
//        if (r.languageCodes() != null && !r.languageCodes().isEmpty()) {
//            var languages = r.languageCodes().stream()
//                .filter(Objects::nonNull)
//                .toList();
//            if (!languages.isEmpty()) {
//                predicates.add(contentRoot.get(COLUMN_LANGUAGE_CODES).in(languages));
//            }
//        }
//        if (r.genreIds() != null && !r.genreIds().isEmpty()) {
//            var genres = r.genreIds().stream()
//                .filter(Objects::nonNull)
//                .toList();
//            if (!genres.isEmpty()) {
//                Join<Content, Genre> genreJoin = contentRoot.join(TABLE_GENRES);
//                predicates.add(genreJoin.get(COLUMN_GENRE_ID).in(genres));
//            }
//        }
//    }
//
//    private void buildPrivatePredicates(
//        List<Predicate> predicates,
//        PrivateSearchRequestDto pr,
//        CriteriaBuilder cb,
//        Root<Content> contentRoot
//    ) {
//        if (pr.uuid() != null) {
//            predicates.add(cb.equal(contentRoot.get(COLUMN_ID), pr.uuid()));
//        }
//        if (pr.createdAtFrom() != null) {
//            predicates.add(cb.greaterThanOrEqualTo(contentRoot.get(COLUMN_CREATED_AT), pr.createdAtFrom()));
//        }
//        if (pr.createdAtTo() != null) {
//            predicates.add(cb.lessThanOrEqualTo(contentRoot.get(COLUMN_CREATED_AT), pr.createdAtTo()));
//        }
//        if (pr.updatedAtFrom() != null) {
//            predicates.add(cb.greaterThanOrEqualTo(contentRoot.get(COLUMN_UPDATED_AT), pr.updatedAtFrom()));
//        }
//        if (pr.updatedAtTo() != null) {
//            predicates.add(cb.lessThanOrEqualTo(contentRoot.get(COLUMN_UPDATED_AT), pr.updatedAtTo()));
//        }
//        if (pr.active() != null) {
//            predicates.add(cb.equal(contentRoot.get(COLUMN_ACTIVE), pr.active()));
//        }
//    }
//
//    private List<Order> getOrders(Sort sort, CriteriaBuilder cb, Root<Content> root) {
//        Set<String> allowedColumns = Set.of(
//            COLUMN_RELEASE_DATE, COLUMN_CREATED_AT, COLUMN_UPDATED_AT, COLUMN_SORT
//        );
//        List<Order> orders = new ArrayList<>();
//        sort.forEach(order -> {
//            String property = order.getProperty();
//            if (allowedColumns.contains(property)) {
//                if (order.isAscending()) {
//                    orders.add(cb.asc(root.get(property)));
//                } else {
//                    orders.add(cb.desc(root.get(property)));
//                }
//            }
//        });
//        return orders;
//    }
//}