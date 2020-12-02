package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.controller.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();
    public List<Employee> getAll() {
        return employees;
    }
}
