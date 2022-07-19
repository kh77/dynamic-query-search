package com.sm.search.orm.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String phone;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;
}
