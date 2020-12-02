package com.thoughtworks.springbootemployee.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getAll() {
        return employees;
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable Integer employeeId) {
        return employees.stream().filter(employee -> employeeId.equals(employee.getId())).findFirst().get();
    }

    @PostMapping
    public Employee create(@RequestBody Employee employee) {
        employees.add(employee);
        return employee;
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable Integer employeeId, @RequestBody Employee employeeUpdate) {
        employees.stream().filter(employee -> employeeId.equals(employee.getId())).findFirst().ifPresent(employee -> {
            employees.remove(employee);
            employees.add(employeeUpdate);
        });
        return employeeUpdate;
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer employeeId) {
        employees.stream().filter(employee -> employeeId.equals(employee.getId())).findFirst().ifPresent(employee -> {
            employees.remove(employee);
        });
    }
}

