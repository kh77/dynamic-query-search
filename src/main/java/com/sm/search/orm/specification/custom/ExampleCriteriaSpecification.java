package com.sm.search.orm.specification.custom;

import com.sm.search.controller.request.ExampleSearchCriteria;
import com.sm.search.orm.entity.Department;
import com.sm.search.orm.entity.Employee;
import com.sm.search.orm.specification.SearchPageSpecification;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class ExampleCriteriaSpecification extends SearchPageSpecification<SearchCriteria<ExampleSearchCriteria>, Employee> {

    private static final long serialVersionUID = 1L;

    public ExampleCriteriaSpecification(SearchCriteria<ExampleSearchCriteria> searchPage) {
        super(searchPage);
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
                                 SearchCriteria<ExampleSearchCriteria> search) {

        ExampleSearchCriteria searchRequest = search.getSearch();

        List<Predicate> predicates = new ArrayList<Predicate>();

        if (StringUtils.isNotEmpty(searchRequest.getName())) {
            Predicate likeFirstName = criteriaBuilder.like(root.get("name"), "%" + searchRequest.getName() + "%");
            predicates.add(likeFirstName);
        }

        if (StringUtils.isNotEmpty(searchRequest.getPhone())) {
            Predicate likeLastName = criteriaBuilder.like(root.get("phone"), "%" + searchRequest.getPhone() + "%");
            predicates.add(likeLastName);
        }

        if (searchRequest.getDepartmentId() != null) {
            Join<Employee, Department> department = root.join("department", JoinType.INNER);
            Predicate eqDeptNo = criteriaBuilder.equal(department.get("id"), searchRequest.getDepartmentId());
            predicates.add(eqDeptNo);
        }

        if (StringUtils.isNotEmpty(searchRequest.getDepartmentName())) {
            Join<Employee, Department> department = root.join("department", JoinType.INNER);
            Predicate likeDeptName = criteriaBuilder.like(department.get("name"), "%" + searchRequest.getDepartmentName() + "%");
            predicates.add(likeDeptName);
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    @Override
    protected String sortProperty(String sortField) {
        switch (sortField) {
            case "name":
                return "name";
            case "departmentId":
                return "department.id";
            case "departmentName":
                return "department.name";
            default:
                return sortField;
        }
    }

}
