package com.sm.search.orm.specification.custom;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

import com.sm.search.controller.request.ExampleSearchWithPage;
import com.sm.search.orm.entity.Department;
import com.sm.search.orm.entity.Employee;
import com.sm.search.orm.specification.SearchPageSpecification;

public class ExampleWithPageSpecification extends SearchPageSpecification<ExampleSearchWithPage, Employee> {

	private static final long serialVersionUID = 1L;

	public ExampleWithPageSpecification(ExampleSearchWithPage searchPage) {
		super(searchPage);
	}

	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder,
			ExampleSearchWithPage search) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();

		if (StringUtils.isNotEmpty(search.getName())) {
			Predicate likeFirstName = criteriaBuilder.like(root.get("name"), "%" + search.getName() + "%" );
			predicates.add(likeFirstName);
		}

		if (StringUtils.isNotEmpty(search.getPhone())) {
			Predicate likeLastName = criteriaBuilder.like(root.get("phone"), "%" + search.getPhone() + "%" );
			predicates.add(likeLastName);
		}

		if (search.getDepartmentId() != null) {
			Join<Employee, Department> department = root.join("department", JoinType.INNER);
			Predicate eqDeptNo = criteriaBuilder.equal(department.get("id"), search.getDepartmentId());
			predicates.add(eqDeptNo);
		}

		if (StringUtils.isNotEmpty(search.getDepartmentName())) {
			Join<Employee, Department> department = root.join("department", JoinType.INNER);
			Predicate likeDeptName = criteriaBuilder.like(department.get("name"),"%" + search.getDepartmentName() + "%" );
			predicates.add(likeDeptName);
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}
	
	@Override
	protected String sortProperty(String sortField) {
		
		if (sortField == null) return "id"; // default if sortField be null
		
		switch (sortField) {
			case "id" : return "department.id";
			case "departmentName" : return "department.name";
			default : return sortField;
		}
	}

}
