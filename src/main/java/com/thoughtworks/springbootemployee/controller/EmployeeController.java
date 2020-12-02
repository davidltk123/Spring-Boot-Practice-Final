package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private List<Employee> employees = new ArrayList<>();

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(params = "gender")
    public List<Employee> getByGender(@RequestParam String gender) {
        return employeeService.getByGender(gender);
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getPaginatedAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        page = page - 1;
        return employees.stream().skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{employeeId}")
    public Employee getById(@PathVariable Integer employeeId) {
        return employees.stream().filter(employee -> employeeId.equals(employee.getId())).findFirst().orElse(null);
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

