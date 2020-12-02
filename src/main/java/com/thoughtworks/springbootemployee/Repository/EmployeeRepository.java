package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Employee;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getAll() {
        return employees;
    }

    public List<Employee> getByGender(String gender) {
        return employees.stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee getById(Integer id) {
        return employees.stream().filter(employee -> id.equals(employee.getId())).findFirst().orElse(null);
    }

    public List<Employee> getPaginatedAll(Integer page, Integer pageSize) {
        page = page - 1;
        return employees.stream().skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee create(Employee employee) {
        this.employees.add(employee);
        return employee;
    }
}
