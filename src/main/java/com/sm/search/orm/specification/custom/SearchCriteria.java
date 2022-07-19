package com.sm.search.orm.specification.custom;

import com.sm.search.orm.specification.SearchPageable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria<T> extends SearchPageable {
    private T search;
}
