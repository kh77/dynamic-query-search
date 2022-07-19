package com.sm.search.orm.specification;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Getter
@Setter
public abstract class SearchSpecification<S, T> implements Specification<T> {

    private static final long serialVersionUID = 1L;

    private S search;

    public SearchSpecification(S search) {
        this.search = search;
    }

    public abstract Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, S search);

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return this.toPredicate(root, query, criteriaBuilder, this.search);
    }

    public S getSearch() {
        return search;
    }
}
