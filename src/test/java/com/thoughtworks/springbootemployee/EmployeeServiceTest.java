package com.thoughtworks.springbootemployee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.Service.EmployeeService;
import com.thoughtworks.springbootemployee.Model.Employee;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService employeeService;
    @Mock
    EmployeeRepository employeeRepository;

    @Test
    public void should_return_all_employees_when_get_all_given_all_employees() {
        //given
        final List<Employee> expected = Arrays.asList(
                new Employee(1,"david",22,"male",11111),
                new Employee(1,"peter",22,"male",11111)

        );
        when(employeeRepository.getAll()).thenReturn(expected);

        //when
        final List<Employee> employees = employeeService.getAll();

        //then
        assertEquals(expected,employees);
    }

    @Test
    public void should_return_all_male_employees_when_get_by_gender_given_gender_is_male() {
        //given
        final List<Employee> expected = Arrays.asList(
                new Employee(1,"david",22,"male",11111),
                new Employee(1,"peter",22,"male",11111)
        );
        when(employeeRepository.getByGender("male")).thenReturn(expected);

        //when
        final List<Employee> employees = employeeService.getByGender("male");

        //then
        assertEquals(expected,employees);
    }

    @Test
    public void should_return_specific_employee_when_get_by_id_given_valid_employee_id() {
        //given
        final Employee expected = new Employee(1,"david",22,"male",11111);
        when(employeeRepository.getById(1)).thenReturn(expected);

        //when
        final Employee employees = employeeService.getById(1);

        //then
        assertEquals(expected,employees);
    }

    @Test
    public void should_2_employees_when_get_paginated_all_given_3_employees_and_page_is_1_and_page_size_is_2() {
        //given
        final List<Employee> expected = Arrays.asList(
                new Employee(1,"david",22,"male",11111),
                new Employee(1,"peter",22,"male",11111)
        );
        when(employeeRepository.getPaginatedAll(1,2)).thenReturn(expected);

        //when
        final List<Employee>  employees = employeeService.getPaginatedAll(1,2);

        //then
        assertEquals(expected,employees);
    }

}
