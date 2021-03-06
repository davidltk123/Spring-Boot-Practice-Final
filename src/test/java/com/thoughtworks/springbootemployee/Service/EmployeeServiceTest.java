package com.thoughtworks.springbootemployee.Service;

import com.thoughtworks.springbootemployee.Exception.EmployeeNotFoundException;
import com.thoughtworks.springbootemployee.Model.Employee;
import com.thoughtworks.springbootemployee.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
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
                new Employee("david", 22, "male", 11111, "123"),
                new Employee("peter", 22, "male", 11111, "123")
        );
        when(employeeRepository.findAll()).thenReturn(expected);

        //when
        final List<Employee> employees = employeeService.getAll();

        //then
        assertEquals(expected, employees);
    }

    @Test
    public void should_return_all_male_employees_when_get_by_gender_given_gender_is_male() {
        //given
        final List<Employee> expected = Arrays.asList(
                new Employee("david", 22, "male", 11111, "123"),
                new Employee("peter", 22, "male", 11111, "123")
        );
        when(employeeRepository.findAllByGender("male")).thenReturn(expected);

        //when
        final List<Employee> employees = employeeService.getByGender("male");

        //then
        assertEquals(expected, employees);
    }

    @Test
    public void should_return_specific_employee_when_get_by_id_given_valid_employee_id() {
        //given
        final Employee expected = new Employee("david", 22, "male", 11111, "123");
        when(employeeRepository.findById("1")).thenReturn(Optional.of(expected));

        //when
        final Employee employees = employeeService.getById("1");

        //then
        assertEquals(expected, employees);
    }

    @Test
    public void should_throw_employee_not_found_exception_when_get_by_id_given_invalid_employee_id() {
        //given
        when(employeeRepository.findById(any())).thenReturn(Optional.empty());

        //when
        EmployeeNotFoundException employeeNotFoundException = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.getById("99999");
        });

        //then
        assertEquals("Employee Not Found!", employeeNotFoundException.getMessage());
    }

    @Test
    public void should_return_2_employees_when_get_paginated_all_given_3_employees_and_page_is_0_and_page_size_is_2() {
        //given
        final List<Employee> allEmployees = Arrays.asList(
                new Employee("david", 22, "male", 11111, "123"),
                new Employee("peter", 22, "male", 11111, "123"),
                new Employee("amy", 22, "female", 11111, "123")
        );
        when(employeeRepository.findAll()).thenReturn(allEmployees);

        //when
        final List<Employee> employees = employeeService.getPaginatedAll(0, 2);

        //then
        assertEquals(2, employees.size());
    }

    @Test
    public void should_return_created_employee_when_create_given_no_employee_in_the_database() {
        //given
        final Employee expected = new Employee("david", 22, "male", 11111, "123");
        when(employeeRepository.save(expected)).thenReturn(expected);

        //when
        employeeService.save(expected);
        ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).save(employeeArgumentCaptor.capture());

        //then
        final Employee employee = employeeArgumentCaptor.getValue();
        assertEquals(expected, employee);
    }

    @Test
    public void should_return_updated_employee_when_update_given_valid_employee_id() {
        //given
        final Employee originalEmployee = new Employee("david", 22, "male", 11111, "123");
        final Employee updatedEmployee = new Employee("david", 44, "male", 11111, "123");
        when(employeeRepository.findById("1")).thenReturn(Optional.of(originalEmployee));
        when(employeeRepository.save(updatedEmployee)).thenReturn(updatedEmployee);

        //when
        final Employee employee = employeeService.update("1", updatedEmployee);

        //then
        assertEquals(updatedEmployee, employee);
    }

    @Test
    public void should_throw_employee_not_found_exception_when_update_given_invalid_employee_id() {
        //given
        final Employee employee = new Employee("david", 44, "male", 11111, "123");
        when(employeeRepository.findById(any())).thenReturn(Optional.empty());

        //when
        EmployeeNotFoundException employeeNotFoundException = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.update("99999", employee);
        });

        //then
        assertEquals("Employee Not Found!", employeeNotFoundException.getMessage());
    }

    @Test
    public void should_delete_specific_employee_when_delete_given_valid_employee_id() {
        //given
        final Employee expected = new Employee("david", 22, "male", 11111, "123");
        when(employeeRepository.findById("1")).thenReturn(Optional.of(expected));

        //when
        employeeService.delete("1");

        //then
        verify(employeeRepository, times(1)).deleteById(expected.getId());
    }

    @Test
    public void should_throw_employee_not_found_exception_when_delete_given_invalid_employee_id() {
        //given
        final Employee employee = new Employee("david", 44, "male", 11111, "123");
        when(employeeRepository.findById(any())).thenReturn(Optional.empty());

        //when
        EmployeeNotFoundException employeeNotFoundException = assertThrows(EmployeeNotFoundException.class, () -> {
            employeeService.delete("99999");
        });

        //then
        assertEquals("Employee Not Found!", employeeNotFoundException.getMessage());
    }

}
