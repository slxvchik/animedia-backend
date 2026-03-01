package dev.animedia.contentservice.content.repository;

import dev.animedia.contentservice.content.dto.request.CommonSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
import dev.animedia.contentservice.content.model.Content;
import dev.animedia.contentservice.content.model.ContentTranslation;
import dev.animedia.contentservice.genre.model.Genre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Repository
public class ContentSearchRepositoryImpl implements ContentSearchRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public Page<UUID> search(PrivateSearchRequestDto searchRequestDto) {
        return executeSearch(searchRequestDto, searchRequestDto.pageable());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UUID> search(PublicSearchRequestDto searchRequestDto) {
        return executeSearch(searchRequestDto, searchRequestDto.pageable());
    }

    private Page<UUID> executeSearch(CommonSearchRequestDto request, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Content> countRoot = countQuery.from(Content.class);

        List<Predicate> countPredicates = buildPredicates(request, cb, countRoot);

        countQuery.select(cb.countDistinct(countRoot.get("uuid")));
        if (!countPredicates.isEmpty()) {
            countQuery.where(countPredicates.toArray(new Predicate[0]));
        }

        Long total = entityManager.createQuery(countQuery).getSingleResult();

        if (total == 0) {
            return Page.empty(pageable);
        }

        CriteriaQuery<UUID> query = cb.createQuery(UUID.class);
        Root<Content> contentRoot = query.from(Content.class);

        List<Predicate> dataPredicates = buildPredicates(request, cb, contentRoot);

        query.select(contentRoot.get("uuid")).distinct(true);
        if (!dataPredicates.isEmpty()) {
            query.where(dataPredicates.toArray(new Predicate[0]));
        }

        if (pageable.getSort().isSorted()) {
            query.orderBy(getOrders(pageable.getSort(), cb, contentRoot));
        }

        int offset = (int) Math.min(pageable.getOffset(), Integer.MAX_VALUE);
        int limit = pageable.getPageSize();

        List<UUID> contentIds = entityManager.createQuery(query)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();

        return new PageImpl<>(contentIds, pageable, total);
    }

    private List<Predicate> buildPredicates(CommonSearchRequestDto r, CriteriaBuilder cb, Root<Content> contentRoot) {
        List<Predicate> predicates = new ArrayList<>();

        buildCommonPredicates(predicates, r, cb, contentRoot);

        if (r instanceof PrivateSearchRequestDto pr) {
            buildPrivatePredicates(predicates, pr, cb, contentRoot);
        }

        return predicates;
    }

    private void buildCommonPredicates(
        List<Predicate> predicates,
        CommonSearchRequestDto r,
        CriteriaBuilder cb,
        Root<Content> contentRoot
    ) {
        if (r.alias() != null && !r.alias().isBlank()) {
            predicates.add(
                cb.like(contentRoot.get("alias"),
                "%" + r.alias() + "%")
            );
        }
        if (r.title() != null && !r.title().isBlank()) {
            Join<Content, ContentTranslation> translationJoin = contentRoot.join("translations");
            predicates.add(
                cb.like(
                    translationJoin.get("title"),
                    "%" + r.title() + "%"
                )
            );
        }
        if (r.type() != null) {
            predicates.add(cb.equal(contentRoot.get("type"), r.type()));
        }
        if (r.seasons() != null && !r.seasons().isEmpty()) {
            var seasons = r.seasons().stream()
                .filter(Objects::nonNull)
                .toList();
            if (!seasons.isEmpty()) {
                predicates.add(contentRoot.get("season").in(seasons));
            }
        }
        if (r.contentStatusIds() != null && !r.contentStatusIds().isEmpty()) {
            var statusIds = r.contentStatusIds().stream()
                .filter(Objects::nonNull)
                .toList();
            if (!statusIds.isEmpty()) {
                predicates.add(contentRoot.get("status").get("id").in(statusIds));
            }
        }
        if (r.releaseFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(contentRoot.get("releaseDate"), r.releaseFrom()));
        }
        if (r.releaseTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(contentRoot.get("releaseDate"), r.releaseTo()));
        }
        if (r.languageCodes() != null && !r.languageCodes().isEmpty()) {
            var languages = r.languageCodes().stream()
                .filter(Objects::nonNull)
                .toList();
            if (!languages.isEmpty()) {
                predicates.add(contentRoot.get("languageCodes").in(languages));
            }
        }
        if (r.genreIds() != null && !r.genreIds().isEmpty()) {
            var genres = r.genreIds().stream()
                .filter(Objects::nonNull)
                .toList();
            if (!genres.isEmpty()) {
                Join<Content, Genre> genreJoin = contentRoot.join("genres");
                predicates.add(genreJoin.get("id").in(genres));
            }
        }
    }

    private void buildPrivatePredicates(
        List<Predicate> predicates,
        PrivateSearchRequestDto pr,
        CriteriaBuilder cb,
        Root<Content> contentRoot
    ) {
        if (pr.uuid() != null) {
            predicates.add(cb.equal(contentRoot.get("uuid"), pr.uuid()));
        }
        if (pr.createdAtFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(contentRoot.get("createdAt"), pr.createdAtFrom()));
        }
        if (pr.createdAtTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(contentRoot.get("createdAt"), pr.createdAtTo()));
        }
        if (pr.updatedAtFrom() != null) {
            predicates.add(cb.greaterThanOrEqualTo(contentRoot.get("updatedAt"), pr.updatedAtFrom()));
        }
        if (pr.updatedAtTo() != null) {
            predicates.add(cb.lessThanOrEqualTo(contentRoot.get("updatedAt"), pr.updatedAtTo()));
        }
        if (pr.active() != null) {
            predicates.add(cb.equal(contentRoot.get("active"), pr.active()));
        }
    }

    private List<Order> getOrders(Sort sort, CriteriaBuilder cb, Root<Content> root) {
        Set<String> allowedColumns = Set.of(
            "releaseDate", "createdAt", "updatedAt", "sort"
        );
        List<Order> orders = new ArrayList<>();
        sort.forEach(order -> {
            String property = order.getProperty();
            if (allowedColumns.contains(property)) {
                if (order.isAscending()) {
                    orders.add(cb.asc(root.get(property)));
                } else {
                    orders.add(cb.desc(root.get(property)));
                }
            }
        });
        return orders;
    }
}