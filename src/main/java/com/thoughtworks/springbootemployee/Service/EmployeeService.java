package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
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
        return employeeRepository.findAll();
    }

    public List<Employee> getByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee getById(String id) {
        try {
            return employeeRepository.findById(id).orElse(null);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found!");
        }
        //return employeeRepository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found!"));

    }

    public List<Employee> getPaginatedAll(Integer page, Integer pageSize) {
        return employeeRepository.findAll().stream().skip(page * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee update(String id, Employee employeeUpdate) {
        if (getById(id) != null) {
            employeeUpdate.setId(id);
            return employeeRepository.save(employeeUpdate);
        }
        return null;
    }

    public void delete(String id) {
        if (getById(id) != null) {
            employeeRepository.deleteById(id);
        }
    }
}
