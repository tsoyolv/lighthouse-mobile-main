package ru.lighthouse.mobile.main.core.dao.search;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import ru.lighthouse.mobile.main.core.dao.DomainModel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;


@RequiredArgsConstructor
public class SearchSpecification<T extends DomainModel> implements Specification<T> {

    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        final String operation = criteria.getOperation().trim().toLowerCase();
        return switch (operation) {
            case ">" -> builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case "<" -> builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString());
            case ">=" -> builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            case "<=" -> builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
            case "=" -> builder.equal(root.get(criteria.getKey()), criteria.getValue());
            case "like" -> builder.like(root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            case "in" -> builder.in(root.get(criteria.getKey())).in(criteria.getValue().toString().split(","));
            default -> null;
        };
    }
}
