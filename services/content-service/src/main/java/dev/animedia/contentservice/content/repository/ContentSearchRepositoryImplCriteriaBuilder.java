//package dev.animedia.contentservice.content.repository;
//
//import dev.animedia.contentservice.content.dto.request.PrivateSearchRequestDto;
//import dev.animedia.contentservice.content.dto.request.PublicSearchRequestDto;
//import dev.animedia.contentservice.content.model.Content;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jakarta.persistence.TypedQuery;
//import jakarta.persistence.criteria.*;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Repository;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class ContentSearchRepositoryImplCriteriaBuilder implements ContentSearchRepository {
//
//	@PersistenceContext
//	private EntityManager entityManager;
//
//	@Override
//	public Page<Content> search(PrivateSearchRequestDto r) {
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Content> query = cb.createQuery(Content.class);
//		Root<Content> contentRoot = query.from(Content.class);
//
//		List<Predicate> predicates = new ArrayList<>();
//
//		addIfNotNull(predicates, r.uuid(), v -> cb.equal(contentRoot.get("uuid"), v));
//		addIfNotNull(predicates, r.alias(), v -> cb.like(cb.lower(contentRoot.get("alias")), "%" + v.toLowerCase() + "%"));
//		addIfNotNull(predicates, r.type(), v -> cb.equal(contentRoot.get("type"), v));
//		if (r.seasons() != null && !r.seasons().isEmpty()) predicates.add(contentRoot.get("season").in(r.seasons()));
//		if (r.contentStatuses() != null && !r.contentStatuses().isEmpty()) predicates.add(contentRoot.get("status").in(r.contentStatuses()));
//		addIfNotNull(predicates, r.releaseFrom(), v -> cb.greaterThanOrEqualTo(contentRoot.get("releaseDate"), v));
//		addIfNotNull(predicates, r.releaseTo(), v -> cb.lessThanOrEqualTo(contentRoot.get("releaseDate"), v));
//		addIfNotNull(predicates, r.createdAtFrom(), v -> cb.greaterThanOrEqualTo(contentRoot.get("createdAt"), v));
//		addIfNotNull(predicates, r.createdAtTo(), v -> cb.lessThanOrEqualTo(contentRoot.get("createdAt"), v));
//		addIfNotNull(predicates, r.updatedAtFrom(), v -> cb.greaterThanOrEqualTo(contentRoot.get("updatedAt"), v));
//		addIfNotNull(predicates, r.updatedAtTo(), v -> cb.lessThanOrEqualTo(contentRoot.get("updatedAt"), v));
//		addIfNotNull(predicates, r.active(), v -> cb.equal(contentRoot.get("active"), v));
//
//		if (r.languageCodes() != null && !r.languageCodes().isEmpty()) {
//			predicates.add(contentRoot.join("languageCodes").in(r.languageCodes()));
//			query.distinct(true);
//		}
//
//		query.where(predicates.toArray(new Predicate[0]));
//
//		query.orderBy(getOrders(r.pageable().getSort(), cb, contentRoot));
//
//		TypedQuery<Content> typedQuery = entityManager.createQuery(query);
//		typedQuery.setFirstResult((int) r.pageable().getOffset());
//		typedQuery.setMaxResults(r.pageable().getPageSize());
//
//		List<Content> results = typedQuery.getResultList();
//
//		long totalCount = countAll(predicates);
//
//		return new PageImpl<>(results, r.pageable(), totalCount);
//	}
//
//	@Override
//	public Page<Content> search(PublicSearchRequestDto r) {
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Content> query = cb.createQuery(Content.class);
//		Root<Content> contentRoot = query.from(Content.class);
//
//		List<Predicate> predicates = new ArrayList<>();
//
//		addIfNotNull(predicates, r.alias(), v -> cb.like(cb.lower(contentRoot.get("alias")), "%" + v.toLowerCase() + "%"));
//		addIfNotNull(predicates, r.type(), v -> cb.equal(contentRoot.get("type"), v));
//		if (r.seasons() != null && !r.seasons().isEmpty()) predicates.add(contentRoot.get("season").in(r.seasons()));
//		if (r.contentStatuses() != null && !r.contentStatuses().isEmpty()) predicates.add(contentRoot.get("status").in(r.contentStatuses()));
//		addIfNotNull(predicates, r.releaseFrom(), v -> cb.greaterThanOrEqualTo(contentRoot.get("releaseDate"), v));
//		addIfNotNull(predicates, r.releaseTo(), v -> cb.lessThanOrEqualTo(contentRoot.get("releaseDate"), v));
//
//		if (r.languageCodes() != null && !r.languageCodes().isEmpty()) {
//			predicates.add(contentRoot.join("languageCodes").in(r.languageCodes()));
//			query.distinct(true);
//		}
//
//		query.where(predicates.toArray(new Predicate[0]));
//
//		query.orderBy(getOrders(r.pageable().getSort(), cb, contentRoot));
//
//		TypedQuery<Content> typedQuery = entityManager.createQuery(query);
//		typedQuery.setFirstResult((int) r.pageable().getOffset());
//		typedQuery.setMaxResults(r.pageable().getPageSize());
//
//		List<Content> results = typedQuery.getResultList();
//
//		long totalCount = countAll(predicates);
//
//		return new PageImpl<>(results, r.pageable(), totalCount);
//	}
//
//	private <T> void addIfNotNull(List<Predicate> predicates, T value, java.util.function.Function<T, Predicate> function) {
//		if (value != null) predicates.add(function.apply(value));
//	}
//
//	private List<Order> getOrders(Sort sort, CriteriaBuilder cb, Root<Content> root) {
//		List<Order> orders = new ArrayList<>();
//		sort.forEach(order -> {
//			if (order.isAscending()) {
//				orders.add(cb.asc(root.get(order.getProperty())));
//			} else {
//				orders.add(cb.desc(root.get(order.getProperty())));
//			}
//		});
//		return orders;
//	}
//
//	private long countAll(List<Predicate> predicates) {
//		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
//		Root<Content> root = countQuery.from(Content.class);
//
//		countQuery.select(cb.countDistinct(root))
//			.where(predicates.toArray(new Predicate[0]));
//
//		return entityManager.createQuery(countQuery).getSingleResult();
//	}
//}
