package com.sm.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.github.javafaker.Faker;
import com.sm.search.orm.entity.Department;
import com.sm.search.orm.entity.Employee;
import com.sm.search.orm.repository.DepartmentRepository;
import com.sm.search.orm.repository.EmployeeRepository;

@SpringBootApplication
public class BootApplication implements CommandLineRunner {

	@Autowired
	ApplicationContext context;
	
	public static void main(String[] args) {
		SpringApplication.run(BootApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		dumpData();
	}

	private void dumpData() {
		EmployeeRepository employeeRepository = context.getBean(EmployeeRepository.class);
		DepartmentRepository departmentRepository = context.getBean(DepartmentRepository.class);

		Faker faker = Faker.instance();

		List<Department> departments = new ArrayList<>();
		for (int i = 1; i <= 9; i++) {
			Department department = new Department();
			department.setName(faker.team().name());
			departments.add(department);
		}

		departments = departmentRepository.saveAll(departments);

		List<Employee> employees = new ArrayList<>();

		for (int i = 0; i < 200; i++) {

			Employee employee = new Employee();
			employee.setName(faker.name().firstName());
			employee.setPhone(faker.phoneNumber().phoneNumber());
			employee.setDepartment(departments.get(new Random().nextInt(departments.size())));
			employees.add(employee);
		}

		employeeRepository.saveAll(employees);

	}

}
