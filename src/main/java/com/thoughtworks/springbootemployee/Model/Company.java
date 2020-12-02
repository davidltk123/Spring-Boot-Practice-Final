package com.thoughtworks.springbootemployee.Model;

import java.util.List;

public class Company {
    private String companyName;
    private Integer employeeNumber;
    private List<Employee> employees;

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
