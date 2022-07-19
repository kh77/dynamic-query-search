package com.sm.search.controller;

import com.sm.search.controller.request.ExampleSearchCriteria;
import com.sm.search.controller.request.ExampleSearchWithPage;
import com.sm.search.controller.request.ExampleSearchWithoutPage;
import com.sm.search.controller.response.EmployeeDetailResponse;
import com.sm.search.orm.entity.Department;
import com.sm.search.orm.entity.Employee;
import com.sm.search.orm.repository.EmployeeRepository;
import com.sm.search.orm.specification.custom.ExampleWithoutPageSpecification;
import com.sm.search.orm.specification.custom.ExampleWithPageSpecification;
import com.sm.search.orm.specification.custom.ExampleCriteriaSpecification;
import com.sm.search.orm.specification.custom.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(path = "/list")
    public List<EmployeeDetailResponse> searchWithoutPageable(ExampleSearchWithoutPage request) {
        ExampleWithoutPageSpecification specification = new ExampleWithoutPageSpecification(request);
        List<Employee> employees = employeeRepository.findAll(specification);
        Function<Employee, EmployeeDetailResponse> converter = convertToDTO();
        return employees.stream().map(converter).collect(Collectors.toList());
    }

    @GetMapping(path = "/page")
    public Page<EmployeeDetailResponse> searchByPageable(ExampleSearchWithPage searchPage) {
        ExampleWithPageSpecification specification = new ExampleWithPageSpecification(searchPage);
        Page<Employee> page = employeeRepository.findAll(specification, specification.getPageable());
        Function<Employee, EmployeeDetailResponse> converter = convertToDTO();
        return page.map(converter);
    }

    @PostMapping(path = "/search-criteria")
    public Page<EmployeeDetailResponse> searchByCriteria(@RequestBody SearchCriteria<ExampleSearchCriteria> searchCriteria) {
        ExampleCriteriaSpecification specification = new ExampleCriteriaSpecification(searchCriteria);
        Page<Employee> page = employeeRepository.findAll(specification, specification.getPageable());
        Function<Employee, EmployeeDetailResponse> converter = convertToDTO();
        return page.map(converter);
    }

    private Function<Employee, EmployeeDetailResponse> convertToDTO() {
        return source -> {
            EmployeeDetailResponse target = new EmployeeDetailResponse();
            target.setId(source.getId());
            target.setName(source.getName());
            target.setPhone(source.getPhone());
            Department department = source.getDepartment();
            target.setDepartmentId(department.getId());
            target.setDepartmentName(department.getName());
            return target;
        };
    }

}
