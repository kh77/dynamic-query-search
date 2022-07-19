package com.sm.search.orm.specification;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public abstract class SearchPageSpecification<S extends SearchPageable, T> extends SearchSpecification<S, T> {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_SORT_ASC = "ASC";
    public static final String DEFAULT_SORT_DESC = "DESC";

    public SearchPageSpecification(S searchPage) {
        super(searchPage);
    }

    /**
     * Override this method. if you want to customize the sort property.
     */
    protected String sortProperty(String sortField) {
        return sortField;
    }

    /**
     * Override this method. if you want to customize the sort directions.
     */
    protected Sort buildSort(String sort, String sortField) {
        switch (sort) {
            case DEFAULT_SORT_ASC:
                return Sort.by(Sort.Order.asc(sortField));
            case DEFAULT_SORT_DESC:
                return Sort.by(Sort.Order.desc(sortField));
            default:
                return Sort.by(Sort.Order.asc(sortField));
        }
    }

    /**
     * Override this method. if you want to customize Pageable.
     */
    public Pageable getPageable() {
        SearchPageable searchPageable = super.getSearch();
        Integer page = searchPageable.getPage();
        Integer size = searchPageable.getSize();
        String sort = StringUtils.defaultString(searchPageable.getSort(), DEFAULT_SORT_ASC);
        String preSortField = this.sortProperty(searchPageable.getSortField());
        String sortField = StringUtils.defaultString(preSortField, "id");
        return PageRequest.of(page, size, buildSort(sort, sortField));
    }

}
