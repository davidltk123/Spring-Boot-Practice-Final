package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return employeeService.getPaginatedAll(page, pageSize);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/{employeeId}")
    public Employee getById(@PathVariable String employeeId) {
        return employeeService.getById(employeeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable String employeeId, @RequestBody Employee employeeUpdate) {
        return employeeService.update(employeeId, employeeUpdate);
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable String employeeId) {
        employeeService.delete(employeeId);
    }
}

