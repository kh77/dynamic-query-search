package com.sm.search.controller.request;

import lombok.Data;

@Data
public class ExampleSearchWithoutPage {

	private String name;
	private String phone;
	private Long departmentId;
	private String departmentName;
}