package ru.lighthouse.mobile.main.core.dao.search;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.lighthouse.mobile.main.core.dao.EntityModel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.From;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;


@RequiredArgsConstructor
public class SearchSpecification<T extends EntityModel> implements Specification<T> {

    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        final String operation = criteria.getOperation().trim().toLowerCase();
        From<T, ?> from = root;
        String key = criteria.getKey();
        if (key.contains(".")) {
            final String[] split = key.split("\\.");
            from = getOrAddJoin(root, split[0]);
            key = split[1];
        } else if (key.contains("#")) {
            final String[] split = key.split("#");
            if (split.length < 2) {
                getOrAddFetch(root, split[0]);
                return null;
            }
            from = getOrAddFetch(root, split[0]);
            key = split[1];
        }
        return switch (operation) {
            case ">" -> builder.greaterThan(from.get(key), criteria.getValue().toString());
            case "<" -> builder.lessThan(from.get(key), criteria.getValue().toString());
            case ">=" -> builder.greaterThanOrEqualTo(from.get(key), criteria.getValue().toString());
            case "<=" -> builder.lessThanOrEqualTo(from.get(key), criteria.getValue().toString());
            case "=" -> builder.equal(from.get(key), criteria.getValue());
            case "like" -> builder.like(builder.lower(from.get(key)), "%" + criteria.getValue().toString().toLowerCase() + "%");
            case "in" -> from.get(key).in((List)criteria.getValue());
            default -> null;
        };
    }

    private Join<T, ?> getOrAddFetch(Root<T> root, String attributeName) {
        for (Fetch<T, ?> fetch : root.getFetches()) {
            if (fetch.getAttribute().getName().equals(attributeName)) {
                return (Join<T, ?>) fetch;
            }
        }
        final Fetch<T, ?> fetch = root.fetch(attributeName, JoinType.INNER);
        return (Join<T, ?>) fetch;
    }

    private Join<T, ?> getOrAddJoin(Root<T> root, String attributeName) {
        for (Fetch<T, ?> fetch : root.getFetches()) {
            if (fetch.getAttribute().getName().equals(attributeName)) {
                return (Join<T, ?>) fetch;
            }
        }
        for (Join<T, ?> join : root.getJoins()) {
            if (join.getAttribute().getName().equals(attributeName)) {
                return join;
            }
        }
        return root.join(attributeName, JoinType.INNER);
    }
}