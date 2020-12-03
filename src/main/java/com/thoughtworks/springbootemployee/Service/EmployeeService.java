package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }


    public List<Employee> getByGender(String gender) {
        return employeeRepository.getAll().stream().filter(employee -> employee.getGender().equals(gender)).collect(Collectors.toList());
    }

    public Employee getById(Integer id) {
        return employeeRepository.getById(id);
    }

    public List<Employee> getPaginatedAll(Integer page, Integer pageSize) {
        return employeeRepository.getPaginatedAll(page, pageSize);
    }

    public Employee create(Employee employee) {
        return employeeRepository.create(employee);
    }

    public Employee update(Integer id, Employee employeeUpdate) {
        return employeeRepository.update(id, employeeUpdate);
    }

    public void delete(Integer id) {
        employeeRepository.delete(id);
    }
}
