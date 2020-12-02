package com.thoughtworks.springbootemployee.Model;

import java.util.List;

public class Company {
    private Integer id;
    private String companyName;
    private Integer employeesNumber;
    private List<Employee> employees;

    public Company() {
    }

    public Company(Integer id, String companyName, Integer employeesNumber, List<Employee> employees) {
        this.id = id;
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

    public Integer getId() {
        return id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Integer getEmployeesNumber() {
        return employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        final Company other = (Company) obj;
        return this.id == other.id && this.companyName.equals(other.companyName) && this.employeesNumber == other.employeesNumber && this.employees == other.employees;
    }
}
