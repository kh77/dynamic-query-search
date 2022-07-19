package com.sm.search.controller.request;

import com.sm.search.orm.specification.SearchPageable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ExampleSearchWithPage extends SearchPageable {

	private String name;
	private String phone;
	private Long departmentId;
	private String departmentName;
}