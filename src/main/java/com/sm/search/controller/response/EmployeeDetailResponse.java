package com.sm.search.controller.response;

import lombok.Data;

@Data
public class EmployeeDetailResponse {

    private Long id;
    private String name;
    private String phone;
    private Long departmentId;
    private String departmentName;
}
