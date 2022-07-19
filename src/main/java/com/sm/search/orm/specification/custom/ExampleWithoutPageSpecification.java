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

import com.sm.search.controller.request.ExampleSearchWithoutPage;
import com.sm.search.orm.entity.Department;
import com.sm.search.orm.entity.Employee;
import com.sm.search.orm.specification.SearchSpecification;

public class ExampleWithoutPageSpecification extends SearchSpecification<ExampleSearchWithoutPage, Employee> {

	private static final long serialVersionUID = 1L;

	public ExampleWithoutPageSpecification(ExampleSearchWithoutPage search) {
		super(search);
	}

	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, ExampleSearchWithoutPage search) {
		
		Join<Employee, Department> department = root.join("department", JoinType.INNER);
		
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
			Predicate eqDeptNo = criteriaBuilder.equal(department.get("id"), search.getDepartmentId());
			predicates.add(eqDeptNo);
		}
		
		if (StringUtils.isNotEmpty(search.getDepartmentName())) {
			Predicate likeDeptName = criteriaBuilder.like(department.get("name"),"%" + search.getDepartmentName() + "%" );
			predicates.add(likeDeptName);
		}
		
		return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
	}

}
