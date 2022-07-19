package com.sm.search.orm.specification;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class SearchPageable {

    private Integer page = 0;
    private Integer size = 10;
    private String sort;
    private String sortField;

}
