package com.thoughtworks.springbootemployee.Mapper;

import com.thoughtworks.springbootemployee.DTO.EmployeeRequest;
import com.thoughtworks.springbootemployee.DTO.EmployeeResponse;
import com.thoughtworks.springbootemployee.Model.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {
    public Employee toEntity(EmployeeRequest employeeRequest){
        Employee employee = new Employee();

//        employee.setName(employeeRequest.getName());
//        employee.setAge(employeeRequest.getAge());
//        employee.setGender(employeeRequest.getGender());
//        employee.setSalary(employeeRequest.getSalary());

        BeanUtils.copyProperties(employeeRequest, employee);

        return employee;
    }

    public EmployeeResponse toResponse(Employee employee){
        EmployeeResponse employeeResponse = new EmployeeResponse();

//        employeeResponse.setId(employee.getId());
//        employeeResponse.setName(employee.getName());
//        employeeResponse.setAge(employee.getAge());
//        employeeResponse.setGender(employee.getGender());
//        employeeResponse.setSalary(employee.getSalary());

        BeanUtils.copyProperties(employee, employeeResponse);

        return employeeResponse;
    }
}
