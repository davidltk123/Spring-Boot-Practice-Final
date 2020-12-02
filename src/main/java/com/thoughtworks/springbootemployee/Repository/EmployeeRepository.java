package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.controller.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();
    public List<Employee> getAll() {
        return employees;
    }
}
