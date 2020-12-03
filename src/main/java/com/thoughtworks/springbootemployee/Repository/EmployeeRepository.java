package com.thoughtworks.springbootemployee.Repository;

import com.thoughtworks.springbootemployee.Model.Employee;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeRepository {
    private List<Employee> employees = new ArrayList<>();

    public List<Employee> getAll() {
        return employees;
    }

    public Employee getById(Integer id) {
        return employees.stream().filter(employee -> id.equals(employee.getId())).findFirst().orElse(null);
    }

    public Employee create(Employee employee) {
        this.employees.add(employee);
        return employee;
    }

    public Employee update(Integer id, Employee employeeUpdate) {
        employees.stream().filter(employee -> id.equals(employee.getId())).findFirst().ifPresent(employee -> {
            employees.remove(employee);
            employees.add(employeeUpdate);
        });
        return employeeUpdate;
    }

    public void delete(String id) {
        employees.stream().filter(employee -> id.equals(employee.getId())).findFirst().ifPresent(employee -> {
            employees.remove(employee);
        });
    }
}
