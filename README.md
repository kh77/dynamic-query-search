# SpringBoot-JPA-DynamicQuery-Specification

Create database with the name of demo

### Sample tables

- Employee 
- Department


	

### How to implement

Step 1. Create Repository interface, extends JpaRepository and JpaSpecificationExecutor.

Step 2. Create Specification class, extends SearchSpecification and override toPredicate method for implement search logic.


#### Demo

- Example searching list of Employee.

		GET method 
		URl : http://localhost:8082/api/employee/list?departmentId=&name=&phone=&departmentName=


- Example searching, sorting and paging for GET method.

		GET method 
		URl : http://localhost:8082/api/employee/page?page=0&sort=desc&sortField=departmentName&firstName=&size=100
	

- Example searching, sorting and paging for POST method.

		POST method 
		URl : http://localhost:8082/api/employee/search-criteria
		Request body :
		{
		    "page" : 0,
		    "size" : "10",
		    "sort" : "asc",
		    "sortField" : "id",
		    "search" : {
			"name" : null,
			"phone" : null,
			"departmentId" : null,
			"departmentName" : null
		    }
		}

