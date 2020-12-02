package com.thoughtworks.springbootemployee.Model;

import java.util.List;

public class Company {
    private Integer id;
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employees;

    public Company(){}

    public Company(Integer id, String companyName, Integer employeeNumber, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employeeNumber = employeeNumber;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeeNumber() {
        return employeeNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
